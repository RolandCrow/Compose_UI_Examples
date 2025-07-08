package com.example.combining_composables.database.dbmapper

import com.example.combining_composables.database.model.ColorDbModel
import com.example.combining_composables.database.model.NoteDbModel
import com.example.combining_composables.domain.ColorModel
import com.example.combining_composables.domain.NoteModel

interface DbMapper {
    fun mapNotes(
        noteDbModels: List<NoteDbModel>, colorDbModels: Map<Long,ColorDbModel>
    ):List<NoteModel>
    fun mapNote(noteDbModel: NoteDbModel, colorDbModel: ColorDbModel): NoteModel

    fun mapColors(colorDbModels: List<ColorDbModel>): List<ColorModel>
    fun mapColor(colorDbModel: ColorDbModel): ColorModel

    fun mapDbNote(note: NoteModel): NoteDbModel

    fun mapDbColors(colors: List<ColorModel>): List<ColorDbModel>
    fun mapDbColor(color: ColorModel): ColorDbModel
}