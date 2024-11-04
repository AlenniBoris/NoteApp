package com.example.noteapplication.domain.usecase

import com.example.noteapplication.domain.model.Note
import com.example.noteapplication.domain.repository.NotesRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    private val notesRepository: NotesRepository
) {

    suspend fun invoke(returnSorted: Boolean): List<Note>{
        return if (returnSorted){
            notesRepository.getAllNotesFromDatabaseSortedByPriority()
        } else {
            notesRepository.getAllNotesFromDatabase()
        }
    }

}