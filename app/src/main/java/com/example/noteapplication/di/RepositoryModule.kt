package com.example.noteapplication.di

import com.example.noteapplication.data.repository.NotesRepositoryImpl
import com.example.noteapplication.domain.repository.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsNotesRepository(
        notesRepositoryImpl: NotesRepositoryImpl
    ) : NotesRepository

}