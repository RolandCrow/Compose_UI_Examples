package com.example.combining_composables.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.combining_composables.database.model.NoteDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM NoteDbModel")
    suspend fun getAllSync(): List<NoteDbModel>

    @Query("SELECT * FROM NoteDbModel WHERE id In (:noteIds)")
    suspend fun getNotesByIdSync(noteIds: List<Long>): List<NoteDbModel>

    @Query("SELECT * FROM NoteDbModel WHERE id LIKE :id")
    fun findById(id: Long): Flow<NoteDbModel>

    @Query("SELECT * FROM NoteDbModel WHERE id LIKE :id")
    suspend fun findByIdSync(id: Long): NoteDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noteDbModel: NoteDbModel)

    @Insert
    suspend fun insertAll(vararg noteDbModel: NoteDbModel)

    @Query("DELETE FROM NoteDbModel WHERE id LIKE :id")
    suspend fun delete(id:Long)

    @Query("DELETE FROM NoteDbModel WHERE id IN (:noteIds)")
    suspend fun delete(noteIds: List<Long>)
}