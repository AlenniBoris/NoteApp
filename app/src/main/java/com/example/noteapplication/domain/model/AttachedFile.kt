package com.example.noteapplication.domain.model

data class AttachedFile(
    val noteId: String,
    val filePath: String,
    val fileName: String
)