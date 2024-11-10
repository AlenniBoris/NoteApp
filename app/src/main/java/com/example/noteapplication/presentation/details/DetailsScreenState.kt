package com.example.noteapplication.presentation.details

import com.example.noteapplication.domain.model.Note

data class DetailsScreenState(
    val userNote: Note? = null,
    val isLoading: Boolean = false,
    val someErrorHappened: Boolean = false,
    val errorMessage: String = ""
)