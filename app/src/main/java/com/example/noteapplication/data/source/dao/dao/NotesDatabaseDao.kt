package com.example.noteapplication.data.source.dao.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteapplication.data.source.dao.model.AttachedFileEntity
import com.example.noteapplication.data.source.dao.model.NoteEntity

@Dao
interface NotesDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNoteToDatabase(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNoteFromDatabase(noteEntity: NoteEntity)

    @Query("SELECT * FROM `notes-database` WHERE noteId=:id")
    suspend fun getNoteFromDatabaseById(id: String): NoteEntity

    @Query("SELECT * FROM `notes-database` ORDER BY isPinned DESC")
    suspend fun getAllNotesFromDatabase(): List<NoteEntity>

    @Update(entity = NoteEntity::class)
    suspend fun updateExistingNote(updatedNote: NoteEntity)

    @Query("DELETE FROM `notes-database`")
    suspend fun deleteAllNotes()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAttachedFile(fileEntity: AttachedFileEntity)

    @Query("SELECT * FROM attached_files WHERE noteId = :noteId")
    suspend fun getAttachedFilesForNote(noteId: String): List<AttachedFileEntity>

    @Delete
    suspend fun deleteAttachedFile(fileEntity: AttachedFileEntity)

    @Query("DELETE FROM attached_files WHERE noteId = :noteId")
    suspend fun deleteAllAttachedFilesForNote(noteId: String)

}