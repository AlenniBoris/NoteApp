package com.example.noteapplication.data.source.dao.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "notes-database"
)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val priority: Int,
    val title: String,
    @ColumnInfo(name = "note-content")
    val content: String
)
