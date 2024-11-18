package com.example.noteapplication.presentation.add

import com.example.noteapplication.domain.model.AttachedFile

data class AddScreenState (
    val newNoteTitle: String = "",
    val newNoteContent: String = "",
    val newNotePriority: Int = 0,
    val attachedFilesList: List<AttachedFile> = emptyList()
)