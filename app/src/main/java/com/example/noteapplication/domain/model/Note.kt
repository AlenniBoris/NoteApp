package com.example.noteapplication.domain.model

data class Note(
    val noteId: String,
    val priority: Int,
    val title: String,
    val content: String,
    val contentPreview: String
) : GeneralNote()