package com.example.noteapplication.domain.repository

import com.example.noteapplication.domain.model.GeneralNote
import com.example.noteapplication.domain.model.Note

interface NotesRepository {

    suspend fun addNoteToDatabase(note: Note)

    suspend fun deleteNoteFromDatabase(note: Note)

    suspend fun getNoteFromDatabaseById(id: String): GeneralNote

    suspend fun getAllNotesFromDatabase(): List<GeneralNote>

    suspend fun getAllNotesFromDatabaseSortedByPriority(): List<GeneralNote>

    suspend fun updateExistingNote(updatedNote: Note)

    suspend fun deleteAllNotes()

}