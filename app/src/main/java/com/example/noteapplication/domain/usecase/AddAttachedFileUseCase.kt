package com.example.noteapplication.domain.usecase

import com.example.noteapplication.domain.model.AttachedFile
import com.example.noteapplication.domain.repository.NotesRepository
import javax.inject.Inject

class AddAttachedFileUseCase @Inject constructor(
    private val notesRepository: NotesRepository
) {

    suspend fun invoke(noteId: String, filePath: String, fileName: String){
        val newFile = AttachedFile(
            noteId = noteId,
            filePath = filePath,
            fileName = fileName
        )
        notesRepository.addAttachedFile(newFile)
    }

}