package com.example.combining_composables.domain

import com.example.combining_composables.database.model.ColorDbModel

data class ColorModel(
    val id: Long,
    val name: String,
    val hex: String
) {
    companion object {
        val DEFAULT = with(ColorDbModel.DEFAULT_COLORS.first()) {ColorModel(id,name,hex)}
    }
}
