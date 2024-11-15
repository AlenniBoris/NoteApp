package com.example.noteapplication.domain.usecase

import com.example.noteapplication.domain.repository.NotesRepository
import javax.inject.Inject

class DeleteAllAttachedFilesUseCase @Inject constructor(
    private val notesRepository: NotesRepository
) {

    suspend fun invoke(noteId: String){
        notesRepository.deleteAllAttachedFilesForNote(noteId)
    }

}