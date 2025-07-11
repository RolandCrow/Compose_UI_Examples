package com.example.combining_composables.domain

data class NoteModel(
    val id: Long = NEW_NOTE_ID,
    val title:String = "",
    val content:String = "",
    val isCheckedOff: Boolean? = null,
    val color: ColorModel = ColorModel.DEFAULT
)

const val NEW_NOTE_ID = -1L


