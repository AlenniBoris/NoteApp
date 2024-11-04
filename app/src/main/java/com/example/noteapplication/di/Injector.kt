package com.example.noteapplication.di

import android.app.Application
import androidx.room.Room
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

    @Singleton
    @Provides
    fun provideNotesDatabase(application: Application) : NotesDatabase =
        Room
            .databaseBuilder(
                application,
                NotesDatabase::class.java,
                NOTES_DATABASE_FILE
            )
            .build()

}