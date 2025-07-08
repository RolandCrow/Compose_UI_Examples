package com.example.combining_composables.database.dbmapper

import com.example.combining_composables.database.model.ColorDbModel
import com.example.combining_composables.database.model.NoteDbModel
import com.example.combining_composables.domain.ColorModel
import com.example.combining_composables.domain.NEW_NOTE_ID
import com.example.combining_composables.domain.NoteModel

class DbMapperImpl: DbMapper {
    override fun mapNotes(
        noteDbModels: List<NoteDbModel>,
        colorDbModels: Map<Long, ColorDbModel>
    ): List<NoteModel>  = noteDbModels.map {
        val colorDbModel = colorDbModels[it.colorId]
            ?: throw RuntimeException(
                "Color for colorId: ${it.colorId} was not found. Make sure that all colors are passed to this method"
            )
        mapNote(it,colorDbModel)
    }

    override fun mapNote(noteDbModel: NoteDbModel, colorDbModel: ColorDbModel): NoteModel {
        val color = mapColor(colorDbModel)
        val isCheckedOff = with(noteDbModel) {if(canBeCheckedOff) isCheckedOff else null}
        return with(noteDbModel) {NoteModel(id, title, content, isCheckedOff, color)}
    }

    override fun mapColors(colorDbModels: List<ColorDbModel>): List<ColorModel> =
        colorDbModels.map { mapColor(it) }

    override fun mapColor(colorDbModel: ColorDbModel): ColorModel =
        with(colorDbModel) { ColorModel(id,name,hex) }

    override fun mapDbNote(note: NoteModel): NoteDbModel =
        with(note) {
            val canBeCheckedOff = isCheckedOff!= null
            val isCheckOff = isCheckedOff ?: false
            if(id == NEW_NOTE_ID) {
                NoteDbModel(
                    title = title,
                    content = content,
                    canBeCheckedOff = canBeCheckedOff,
                    isCheckedOff =  isCheckOff,
                    colorId = color.id,
                    isInTrash = false
                )
            } else {
                NoteDbModel(id,title,content,canBeCheckedOff,isCheckOff,color.id,false)
            }
        }

    override fun mapDbColors(colors: List<ColorModel>): List<ColorDbModel> =
        colors.map { mapDbColor(it) }

    override fun mapDbColor(color: ColorModel): ColorDbModel =
        with(color) { ColorDbModel(id, hex, name) }
}