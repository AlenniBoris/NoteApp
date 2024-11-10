package com.example.noteapplication.presentation.add

data class AddScreenState (
    val newNoteTitle: String = "",
    val newNoteContent: String = "",
    val newNotePriority: Int = 3
)