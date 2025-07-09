package com.example.combining_composables.di

import android.content.Context
import androidx.room.Room
import com.example.combining_composables.database.AppDatabase
import com.example.combining_composables.database.dbmapper.DbMapper
import com.example.combining_composables.database.dbmapper.DbMapperImpl
import com.example.combining_composables.repository.Repository
import com.example.combining_composables.repository.RepositoryImpl

class DependencyInjector(context: Context) {
    private val database: AppDatabase by lazy { provideDatabase(context) }
    val repository: Repository by lazy { provideRepository(database) }
    private val dbMapper: DbMapper = DbMapperImpl()

    private fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    private fun provideRepository(database: AppDatabase): Repository {
        val noteDao = database.noteDao()
        val colorDao = database.colorDao()
        return RepositoryImpl(noteDao,colorDao,dbMapper)
    }
}