package com.example.noteapplication.data.source.dao.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapplication.data.source.dao.dao.NotesDatabaseDao
import com.example.noteapplication.data.source.dao.model.AttachedFileEntity
import com.example.noteapplication.data.source.dao.model.NoteEntity

@Database(
    entities = [NoteEntity::class, AttachedFileEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NotesDatabase : RoomDatabase() {
    abstract val dao: NotesDatabaseDao
}