package com.example.noteapplication.di

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.noteapplication.data.source.dao.database.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Injector {

    private const val NOTES_DATABASE_FILE = "database-data.db"

    private val migration = object: Migration(1,2){
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL(
                """
                    CREATE TABLE attached_files (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        noteId TEXT NOT NULL,
                        filePath TEXT NOT NULL,
                        fileName TEXT NOT NULL,
                        FOREIGN KEY(noteId) REFERENCES notes(noteId) ON DELETE CASCADE
                    )
                """.trimIndent()
            )
        }

    }

    @Singleton
    @Provides
    fun provideNotesDatabase(application: Application) : NotesDatabase =
        Room
            .databaseBuilder(
                application,
                NotesDatabase::class.java,
                NOTES_DATABASE_FILE
            )
            .addMigrations(migration)
            .build()

}