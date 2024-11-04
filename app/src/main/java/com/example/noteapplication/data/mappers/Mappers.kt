package com.example.noteapplication.data.mappers

import com.example.noteapplication.data.source.dao.model.NoteEntity
import com.example.noteapplication.domain.model.Note

fun NoteEntity.asNote(): Note{
    return Note(
        id = this.id,
        priority = this.priority,
        title = this.title,
        content = this.content
    )
}

fun Note.asNoteEntity(): NoteEntity{
    return NoteEntity(
        id = this.id,
        priority = this.priority,
        title = this.title,
        content = this.content
    )
}