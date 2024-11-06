package com.example.noteapplication.presentation.home

import com.example.noteapplication.domain.model.GeneralNote
import com.example.noteapplication.domain.model.Note

data class HomeScreenState(
    val listOfNotes: List<Note> = emptyList(),
    val notesAreLoading: Boolean = false,
    val noNotesFound: Boolean = false,
    val someErrorHappened: Boolean = false,
    val errorMessage: String = ""
)