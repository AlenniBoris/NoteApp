package com.example.noteapplication.presentation.details

import android.util.Log
import androidx.core.graphics.rotationMatrix
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapplication.domain.model.ExceptionNote
import com.example.noteapplication.domain.model.Note
import com.example.noteapplication.domain.usecase.AddNoteUseCase
import com.example.noteapplication.domain.usecase.DeleteNoteUseCase
import com.example.noteapplication.domain.usecase.GetNoteByIdUseCase
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
    private val addNoteUseCase: AddNoteUseCase
) : ViewModel() {

    val screenState = MutableStateFlow(DetailsScreenState())

    fun getNoteById(noteId: String){
        viewModelScope.launch(Dispatchers.IO) {
            getNoteByIdInternal(noteId)
        }
    }

    suspend fun getNoteByIdInternal(noteId: String){

        changeIsLoading(true)

        val returnedNote = getNoteByIdUseCase.invoke(noteId)

        changeIsLoading(false)

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
        viewModelScope.launch(Dispatchers.IO) {
            addNoteUseCase.invoke(noteToAdd = updatedNote)
            getNoteByIdInternal(screenState.value.userNote?.noteId.toString())
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