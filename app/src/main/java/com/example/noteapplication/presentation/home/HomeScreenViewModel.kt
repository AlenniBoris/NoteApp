package com.example.noteapplication.presentation.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapplication.domain.model.ExceptionNote
import com.example.noteapplication.domain.model.Note
import com.example.noteapplication.domain.usecase.AddNoteUseCase
import com.example.noteapplication.domain.usecase.DeleteAllNotesUseCase
import com.example.noteapplication.domain.usecase.DeleteNoteUseCase
import com.example.noteapplication.domain.usecase.GetAllNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteAllNotesUseCase: DeleteAllNotesUseCase
): ViewModel(){

    val screenState = MutableStateFlow(HomeScreenState())

    init {
        getNotes(false)
    }

    fun getNotes(returnSorted: Boolean){
        changeIsLoading(true)
        viewModelScope.launch(Dispatchers.IO) {
            getNotesInternal(returnSorted)
        }
        changeIsLoading(false)
    }

    suspend fun getNotesInternal(returnSorted: Boolean){
        val comingList = getAllNotesUseCase.invoke(returnSorted)

        if (comingList.size == 1 && comingList[0] is ExceptionNote){

            val exceptionNote = comingList[0] as ExceptionNote

            screenState.update { state ->
                state.copy(
                    someErrorHappened = true,
                    errorMessage = exceptionNote.exceptionMessage
                )
            }

            return
        }

        screenState.update { state ->
            state.copy(
                listOfNotes = comingList.map { it as Note },
                noNotesFound = comingList.isEmpty()
            )
        }
    }

    fun actionOnSortedButton(){
        if (!screenState.value.isAlreadySorted){
            viewModelScope.launch {
                getNotesInternal(true)
            }
            changeIsAlreadySorted(true)
        }else{
            viewModelScope.launch {
                getNotesInternal(false)
            }
            changeIsAlreadySorted(false)
        }
    }

    fun changeIsAlreadySorted(isSorted: Boolean){
        screenState.update { state ->
            state.copy(
                isAlreadySorted = isSorted
            )
        }
    }

    fun changeIsLoading(loading: Boolean){
        screenState.update { state ->
            state.copy(
                notesAreLoading = loading
            )
        }
    }

    fun deleteAllNotes(){
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllNotesUseCase.invoke()
            getNotesInternal(false)
        }
    }

    fun deleteNoteBySwipe(noteToDelete: Note){
        viewModelScope.launch(Dispatchers.IO) {
            deleteNoteUseCase.invoke(noteToDelete)
            onItemCollapsed(noteToDelete.noteId)
            getNotesInternal(false)
        }
    }

    fun checkIfCardIsInRevealed(nodeId: String): Boolean{
        return screenState.value.listOfRevealedNotesIds.contains(nodeId)
    }

    fun onItemExpanded(nodeId: String){
        if (checkIfCardIsInRevealed(nodeId)) return

        val oldListUpdated = screenState.value.listOfRevealedNotesIds.toMutableList().also {
            it.add(nodeId)
        }
        screenState.update { state ->
            state.copy(
                listOfRevealedNotesIds = oldListUpdated
            )
        }
    }

    fun onItemCollapsed(nodeId: String) {
        if (!checkIfCardIsInRevealed(nodeId)) return

        val oldListUpdated = screenState.value.listOfRevealedNotesIds.toMutableList().also {
            it.remove(nodeId)
        }
        screenState.update { state ->
            state.copy(
                listOfRevealedNotesIds = oldListUpdated
            )
        }
    }

}