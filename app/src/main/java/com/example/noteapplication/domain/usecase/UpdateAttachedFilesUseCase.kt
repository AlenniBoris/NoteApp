package com.example.noteapplication.domain.usecase

import android.util.Log
import com.example.noteapplication.domain.model.AttachedFile
import com.example.noteapplication.domain.repository.NotesRepository
import javax.inject.Inject

class UpdateAttachedFilesUseCase @Inject constructor(
    private val notesRepository: NotesRepository
) {

    suspend fun invoke(noteId: String, newList: List<AttachedFile>) {
        notesRepository.deleteAllAttachedFilesForNote(noteId)
        newList.forEach { file ->
            notesRepository.addAttachedFile(
                AttachedFile(noteId, file.filePath, file.fileName)
            )
        }
    }

}