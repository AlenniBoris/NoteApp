package com.example.noteapplication.domain.usecase

import com.example.noteapplication.domain.model.AttachedFile
import com.example.noteapplication.domain.repository.NotesRepository
import javax.inject.Inject

class GetAttachedFilesUseCase @Inject constructor(
    private val notesRepository: NotesRepository
) {

    suspend fun invoke(noteId: String): List<AttachedFile> {
        return notesRepository.getAttachedFilesForNote(noteId)
    }

}