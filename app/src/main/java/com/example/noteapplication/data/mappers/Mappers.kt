package com.example.noteapplication.data.mappers

import com.example.noteapplication.data.source.dao.model.AttachedFileEntity
import com.example.noteapplication.data.source.dao.model.NoteEntity
import com.example.noteapplication.domain.model.AttachedFile
import com.example.noteapplication.domain.model.Note
import kotlin.concurrent.thread

fun NoteEntity.asNote(): Note{
    return Note(
        noteId = this.noteId,
        priority = this.priority,
        title = this.title,
        content = this.content,
        contentPreview = this.contentPreview,
        isPinned = this.isPinned
    )
}

fun Note.asNoteEntity(): NoteEntity{
    return NoteEntity(
        noteId = this.noteId,
        priority = this.priority,
        title = this.title,
        content = this.content,
        contentPreview = this.contentPreview,
        isPinned = this.isPinned
    )
}

fun AttachedFile.asAttachedFileEntity(): AttachedFileEntity{
    return AttachedFileEntity(
        noteId = this.noteId,
        fileName = this.fileName,
        filePath = this.filePath
    )
}

fun AttachedFileEntity.asAttachedFile(): AttachedFile{
    return AttachedFile(
        noteId = this.noteId,
        fileName = this.fileName,
        filePath = this.filePath
    )
}