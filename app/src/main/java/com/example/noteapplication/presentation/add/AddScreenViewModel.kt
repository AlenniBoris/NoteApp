package com.example.noteapplication.presentation.add

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapplication.domain.model.AttachedFile
import com.example.noteapplication.domain.usecase.AddNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject
import com.example.noteapplication.domain.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class AddScreenViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase
): ViewModel(){

    val screenState = MutableStateFlow(AddScreenState())

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

    fun addAttachedFile(filePath: String, fileName: String){

    }

    fun removeAttachedFile(attachedFile: AttachedFile){

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addNewNote(){
        val currentTime = LocalDateTime.now().toString()
        val newContent = screenState.value.newNoteContent
        val contentPreview = newContent.substring(0, newContent.length/2) + "..."
        val noteToAdd = Note(
            noteId = currentTime,
            priority = if(screenState.value.newNotePriority == 0) 3 else screenState.value.newNotePriority,
            title = screenState.value.newNoteTitle,
            content = screenState.value.newNoteContent,
            contentPreview = contentPreview,
            isPinned = false
        )
        viewModelScope.launch(Dispatchers.IO) {
            addNoteUseCase.invoke(noteToAdd)
        }
    }

}