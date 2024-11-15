package com.example.noteapplication.data.source.dao.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "attached_files",
    foreignKeys = [
        ForeignKey(
            entity = NoteEntity::class,
            parentColumns = ["noteId"],
            childColumns = ["noteId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["noteId"])]
)
data class AttachedFileEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val noteId: String,
    val filePath: String,
    val fileName: String
)
