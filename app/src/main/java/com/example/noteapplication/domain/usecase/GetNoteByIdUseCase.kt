package com.example.noteapplication.domain.usecase

import com.example.noteapplication.domain.model.Note
import com.example.noteapplication.domain.repository.NotesRepository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    private val notesRepository: NotesRepository
) {

    suspend fun invoke(id: Int): Note? {
        return notesRepository.getNoteFromDatabaseById(id)
    }

}