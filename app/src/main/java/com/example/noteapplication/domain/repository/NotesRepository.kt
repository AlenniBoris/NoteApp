package com.example.noteapplication.domain.repository

import com.example.noteapplication.domain.model.Note

interface NotesRepository {

    suspend fun addNoteToDatabase(note: Note)

    suspend fun deleteNoteFromDatabase(note: Note)

    suspend fun getNoteFromDatabaseById(id: Int): Note?

    suspend fun getAllNotesFromDatabase(): List<Note>

    suspend fun getAllNotesFromDatabaseSortedByPriority(): List<Note>

    suspend fun updateExistingNote(updatedNote: Note)

}