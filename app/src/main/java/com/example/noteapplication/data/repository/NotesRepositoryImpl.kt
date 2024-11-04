package com.example.noteapplication.data.repository

import com.example.noteapplication.data.mappers.asNote
import com.example.noteapplication.data.mappers.asNoteEntity
import com.example.noteapplication.data.source.dao.database.NotesDatabase
import com.example.noteapplication.domain.model.Note
import com.example.noteapplication.domain.repository.NotesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesRepositoryImpl @Inject constructor(
    private val database: NotesDatabase
) : NotesRepository{

    override suspend fun addNoteToDatabase(note: Note) {
        database.dao.addNoteToDatabase(note.asNoteEntity())
    }

    override suspend fun deleteNoteFromDatabase(note: Note) {
        database.dao.deleteNoteFromDatabase(note.asNoteEntity())
    }

    override suspend fun getNoteFromDatabaseById(id: Int): Note? {
        return try {
            database.dao.getNoteFromDatabaseById(id).asNote()
        } catch (e: Exception){
            null
        }
    }

    override suspend fun getAllNotesFromDatabase(): List<Note> {
        return try {
            database.dao.getAllNotesFromDatabase().map { it.asNote() }
        } catch (e: Exception){
            emptyList()
        }
    }

    override suspend fun getAllNotesFromDatabaseSortedByPriority(): List<Note> {
        return try {
            database.dao.getAllNotesFromDatabaseSortedByPriority().map { it.asNote() }
        } catch (e: Exception){
            emptyList()
        }
    }

    override suspend fun updateExistingNote(updatedNote: Note) {
        database.dao.updateExistingNote(updatedNote.asNoteEntity())
    }

}