package com.example.noteapplication.domain.model

data class Note(
    val id: Int,
    val priority: Int,
    val title: String,
    val content: String
)