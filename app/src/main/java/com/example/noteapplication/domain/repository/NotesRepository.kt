package com.example.noteapplication.domain.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.noteapplication.data.source.dao.model.AttachedFileEntity
import com.example.noteapplication.domain.model.AttachedFile
import com.example.noteapplication.domain.model.GeneralNote
import com.example.noteapplication.domain.model.Note

interface NotesRepository {

    suspend fun addNoteToDatabase(note: Note)

    suspend fun deleteNoteFromDatabase(note: Note)

    suspend fun getNoteFromDatabaseById(id: String): GeneralNote

    suspend fun getAllNotesFromDatabase(): List<GeneralNote>

    suspend fun updateExistingNote(updatedNote: Note)

    suspend fun deleteAllNotes()

    suspend fun addAttachedFile(attachedFile: AttachedFile)

    suspend fun getAttachedFilesForNote(noteId: String): List<AttachedFile>

    suspend fun deleteAttachedFile(attachedFile: AttachedFile)

    suspend fun deleteAllAttachedFilesForNote(noteId: String)

}