package com.example.noteapplication.domain.usecase

import com.example.noteapplication.domain.model.ExceptionNote
import com.example.noteapplication.domain.model.GeneralNote
import com.example.noteapplication.domain.model.Note
import com.example.noteapplication.domain.repository.NotesRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    private val notesRepository: NotesRepository
) {

    suspend fun invoke(returnSorted: Boolean): List<GeneralNote>{
        val  returnedList = notesRepository.getAllNotesFromDatabase()

        return returnNeededList(returnedList, returnSorted)
    }

    private fun returnNeededList(list: List<GeneralNote>, returnSorted: Boolean): List<GeneralNote>{
        return if (returnSorted && (list[0] !is ExceptionNote)){
            list.sortedBy { (it as Note).priority }
        }else{
            list
        }
    }

}