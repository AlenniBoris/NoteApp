package com.example.noteapplication.presentation.details

import android.util.Log
import androidx.core.graphics.rotationMatrix
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapplication.domain.model.AttachedFile
import com.example.noteapplication.domain.model.ExceptionNote
import com.example.noteapplication.domain.model.Note
import com.example.noteapplication.domain.usecase.AddAttachedFilesUseCase
import com.example.noteapplication.domain.usecase.AddNoteUseCase
import com.example.noteapplication.domain.usecase.DeleteNoteUseCase
import com.example.noteapplication.domain.usecase.GetAttachedFilesUseCase
import com.example.noteapplication.domain.usecase.GetNoteByIdUseCase
import com.example.noteapplication.domain.usecase.UpdateAttachedFilesUseCase
import com.example.noteapplication.domain.usecase.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val getAttachedFilesUseCase: GetAttachedFilesUseCase,
    private val updateAttachedFilesUseCase: UpdateAttachedFilesUseCase
) : ViewModel() {

    val screenState = MutableStateFlow(DetailsScreenState())

    fun getNoteById(noteId: String){
        viewModelScope.launch(Dispatchers.IO) {
            getNoteByIdInternal(noteId)
        }
    }

    fun getAllAttachedFiles(noteId: String){
        viewModelScope.launch(Dispatchers.IO) {
            getAttachedFilesInternal(noteId)
        }
    }

    suspend fun getAttachedFilesInternal(noteId: String){
        val files = getAttachedFilesUseCase.invoke(noteId)
        screenState.update { state ->
            state.copy(
                attachedFiles = files
            )
        }

        updateNoteAttachedFiles(files)
    }

    fun updateNoteAttachedFiles(files: List<AttachedFile>){
        screenState.update { state ->
            state.copy(
                newAttachedFiles = files
            )
        }
    }

    fun addAttachedFile(filePath: String, fileName: String){
        val newFile = AttachedFile(
            noteId = "",
            filePath = filePath,
            fileName = fileName
        )
        val newList =
            if(!screenState.value.attachedFiles.contains(newFile))
                screenState.value.newAttachedFiles + newFile
            else screenState.value.newAttachedFiles
        updateNoteAttachedFiles(newList)
    }

    fun removeAttachedFile(attachedFile: AttachedFile){
        val newList = screenState.value.newAttachedFiles.toMutableList() - attachedFile
        updateNoteAttachedFiles(newList)
    }

    suspend fun getNoteByIdInternal(noteId: String){

        changeIsLoading(true)

        val returnedNote = getNoteByIdUseCase.invoke(noteId)

        if (returnedNote is ExceptionNote){
            screenState.update { state ->
                state.copy(
                    someErrorHappened = true,
                    errorMessage = returnedNote.exceptionMessage
                )
            }
        }

        if (returnedNote is Note){
            screenState.update { state ->
                state.copy(
                    userNote = returnedNote,
                )
            }

            updateNoteTitleText(returnedNote.title)
            updateNoteContentText(returnedNote.content)
            updateNotePriority(returnedNote.priority)
        }

        changeIsLoading(false)
    }

    fun changeIsLoading(loading: Boolean){
        screenState.update { state ->
            state.copy(
                isLoading = loading
            )
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            deleteNoteUseCase.invoke(note)
        }
    }

    fun actionOnRefactoringButton(){
        if (screenState.value.isRefactoring){
            changeIsRefactoring(false)
        } else {
            changeIsRefactoring(true)
        }
    }

    private fun changeIsRefactoring(isRefactoring: Boolean){
        screenState.update { state ->
            state.copy(
                isRefactoring = isRefactoring
            )
        }
    }

    fun updateCurrentNote(){
        val newContent = screenState.value.newNoteContent
        val contentPreview = newContent.substring(0, newContent.length/2) + "..."
        val updatedNote = Note(
            noteId = screenState.value.userNote?.noteId.toString(),
            priority = if(screenState.value.newNotePriority == 0) 3 else screenState.value.newNotePriority,
            title = screenState.value.newNoteTitle,
            content = screenState.value.newNoteContent,
            contentPreview = contentPreview,
            isPinned = false
        )

        val newAttachedFiles = screenState.value.newAttachedFiles

        viewModelScope.launch(Dispatchers.IO) {
            updateNoteUseCase.invoke(updatedNote)
            updateAttachedFilesUseCase.invoke(updatedNote.noteId, newAttachedFiles)

            getNoteByIdInternal(screenState.value.userNote?.noteId.toString())
            getAttachedFilesInternal(screenState.value.userNote?.noteId.toString())
        }
        actionOnRefactoringButton()
    }

    fun updateNoteTitleText(text: String){
        screenState.update { state ->
            state.copy(
                newNoteTitle = text
            )
        }
    }

    fun updateNoteContentText(text: String){
        screenState.update { state ->
            state.copy(
                newNoteContent = text
            )
        }
    }

    fun updateNotePriority(priority: Int){
        screenState.update { state ->
            state.copy(
                newNotePriority = priority
            )
        }
    }
}