package com.example.noteapplication.domain.usecase

import com.example.noteapplication.domain.model.AttachedFile
import com.example.noteapplication.domain.repository.NotesRepository
import javax.inject.Inject

class AddAttachedFilesUseCase @Inject constructor(
    private val notesRepository: NotesRepository
) {

    suspend fun invoke(list: List<AttachedFile>){
        list.forEach { file ->
            notesRepository.addAttachedFile(file)
        }
    }

}