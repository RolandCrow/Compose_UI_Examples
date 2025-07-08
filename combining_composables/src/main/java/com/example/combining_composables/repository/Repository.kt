package com.example.combining_composables.repository

import com.example.combining_composables.domain.ColorModel
import com.example.combining_composables.domain.NoteModel
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getAllNotesNotInTrash(): Flow<List<NoteModel>>
    fun getAllNotesInTrash(): Flow<List<NoteModel>>
    fun getNote(id: Long): Flow<NoteModel>
    suspend fun insertNote(noteModel: NoteModel)
    suspend fun deleteNote(id: Long)
    suspend fun deleteNotes(notesIds: List<Long>)
    suspend fun moveNoteToTrash(noteId: Long)
    suspend fun restoreNotesFromTrash(noteIds: List<Long>)

    fun getAllColors(): Flow<List<ColorModel>>
    suspend fun getAllColorsSync():List<ColorModel>
    fun getColor(id: Long): Flow<ColorModel>
    suspend fun getColorSync(id: Long): ColorModel
}