package com.example.noteapplication.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapplication.domain.model.ExceptionNote
import com.example.noteapplication.domain.model.Note
import com.example.noteapplication.domain.usecase.DeleteNoteUseCase
import com.example.noteapplication.domain.usecase.GetNoteByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
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
                    userNote = returnedNote
                )
            }
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

}