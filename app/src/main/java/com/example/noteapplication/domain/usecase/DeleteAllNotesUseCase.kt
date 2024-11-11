package com.example.noteapplication.domain.usecase

import com.example.noteapplication.domain.repository.NotesRepository
import javax.inject.Inject

class DeleteAllNotesUseCase @Inject constructor(
    private val notesRepository: NotesRepository
) {

    suspend fun invoke(){
        notesRepository.deleteAllNotes()
    }

}