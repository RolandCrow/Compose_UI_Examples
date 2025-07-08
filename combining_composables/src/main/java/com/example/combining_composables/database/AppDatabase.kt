package com.example.combining_composables.database

import androidx.room.Database
import com.example.combining_composables.database.dao.ColorDao
import com.example.combining_composables.database.dao.NoteDao
import com.example.combining_composables.database.model.ColorDbModel
import com.example.combining_composables.database.model.NoteDbModel

@Database(entities = [NoteDbModel::class, ColorDbModel::class], version = 1)
abstract class AppDatabase {

    companion object {
        const val DATABASE_NAME = "note-maker-database"
    }
    abstract fun noteDao(): NoteDao
    abstract fun colorDao(): ColorDao
}