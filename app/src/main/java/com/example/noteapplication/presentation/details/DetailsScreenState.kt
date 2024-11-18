package com.example.noteapplication.presentation.details

import com.example.noteapplication.domain.model.AttachedFile
import com.example.noteapplication.domain.model.Note

data class DetailsScreenState(
    val userNote: Note? = null,
    val isLoading: Boolean = false,
    val someErrorHappened: Boolean = false,
    val errorMessage: String = "",
    val isRefactoring: Boolean = false,
    val newNoteTitle: String = "",
    val newNoteContent: String = "",
    val newNotePriority: Int = 0,
    val attachedFiles: List<AttachedFile> = emptyList(),
    val newAttachedFiles: List<AttachedFile> = emptyList()
)
