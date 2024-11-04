package com.example.noteapplication.data.source.dao.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteapplication.data.source.dao.model.NoteEntity

@Dao
interface NotesDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNoteToDatabase(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNoteFromDatabase(noteEntity: NoteEntity)

    @Query("SELECT * FROM `notes-database` WHERE id=:id")
    suspend fun getNoteFromDatabaseById(id: Int): NoteEntity

    @Query("SELECT * FROM `notes-database`")
    suspend fun getAllNotesFromDatabase(): List<NoteEntity>

    @Query("SELECT * FROM `notes-database` ORDER BY priority DESC")
    suspend fun getAllNotesFromDatabaseSortedByPriority(): List<NoteEntity>

    @Update(entity = NoteEntity::class)
    suspend fun updateExistingNote(updatedNote: NoteEntity)

}