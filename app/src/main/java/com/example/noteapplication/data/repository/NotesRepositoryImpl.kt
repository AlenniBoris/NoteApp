package com.example.noteapplication.data.repository

import android.util.Log
import com.example.noteapplication.data.mappers.asAttachedFile
import com.example.noteapplication.data.mappers.asAttachedFileEntity
import com.example.noteapplication.data.mappers.asNote
import com.example.noteapplication.data.mappers.asNoteEntity
import com.example.noteapplication.data.source.dao.database.NotesDatabase
import com.example.noteapplication.domain.model.AttachedFile
import com.example.noteapplication.domain.model.ExceptionNote
import com.example.noteapplication.domain.model.GeneralNote
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

    override suspend fun getNoteFromDatabaseById(id: String): GeneralNote {
        val returned = try {
            database.dao.getNoteFromDatabaseById(id).asNote()
        } catch (e: Exception){
            ExceptionNote(e.message.toString())
        }

        Log.d("RETURNED", returned.toString())

        return returned
    }

    override suspend fun getAllNotesFromDatabase(): List<GeneralNote> {
        return try {
            database.dao.getAllNotesFromDatabase().map { it.asNote() }
        } catch (e: Exception){
            listOf(ExceptionNote(e.message.toString()))
        }
    }

    override suspend fun updateExistingNote(updatedNote: Note) {
        database.dao.updateExistingNote(updatedNote.asNoteEntity())
    }

    override suspend fun deleteAllNotes() {
        database.dao.deleteAllNotes()
    }

    override suspend fun addAttachedFile(attachedFile: AttachedFile) {
        database.dao.addAttachedFile(attachedFile.asAttachedFileEntity())
    }

    override suspend fun getAttachedFilesForNote(noteId: String): List<AttachedFile> {
        return database.dao.getAttachedFilesForNote(noteId).map { it.asAttachedFile() }
    }

    override suspend fun deleteAttachedFile(attachedFile: AttachedFile) {
        database.dao.deleteAttachedFile(attachedFile.asAttachedFileEntity())
    }

    override suspend fun deleteAllAttachedFilesForNote(noteId: String) {
        database.dao.deleteAllAttachedFilesForNote(noteId)
    }
}