package com.example.combining_composables.repository

import com.example.combining_composables.database.dao.ColorDao
import com.example.combining_composables.database.dao.NoteDao
import com.example.combining_composables.database.dbmapper.DbMapper
import com.example.combining_composables.database.model.ColorDbModel
import com.example.combining_composables.database.model.NoteDbModel
import com.example.combining_composables.domain.ColorModel
import com.example.combining_composables.domain.NoteModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RepositoryImpl(
    private val noteDao: NoteDao,
    private val colorDao: ColorDao,
    private val dbMapper: DbMapper
): Repository {
    private val notesNotInTrashLiveData: MutableStateFlow<List<NoteModel>> by lazy {
        MutableStateFlow(emptyList())
    }
    private val notesInTrashLiveData: MutableStateFlow<List<NoteModel>> by lazy {
        MutableStateFlow(emptyList())
    }

    init {
        initDatabase(this::updateNotesLiveData)
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun initDatabase(postInitAction: suspend () -> Unit) {
        GlobalScope.launch {
            val colors = ColorDbModel.DEFAULT_COLORS.toTypedArray()
            val dbColors = colorDao.getAllSync()
            if(dbColors.isEmpty()) {
                colorDao.insertAll(*colors)
            }

            val notes = NoteDbModel.DEFAULT_NOTES.toTypedArray()
            val dbNotes = noteDao.getAllSync()
            if(dbNotes.isEmpty()) {
                noteDao.insertAll(*notes)
            }
            postInitAction.invoke()
        }
    }

    override fun getAllNotesNotInTrash(): Flow<List<NoteModel>> = notesNotInTrashLiveData

    override fun getAllNotesInTrash(): Flow<List<NoteModel>> = notesInTrashLiveData

    private suspend fun getAllNotesDependingOnTrashStateSync(inTrash: Boolean): List<NoteModel> {
        val colorDbModels: Map<Long,ColorDbModel> = colorDao.getAllSync().associateBy({it.id})
        val dbNotesNotInTrash: List<NoteDbModel> = noteDao.getAllSync().filter { it.isInTrash == inTrash }
        return dbMapper.mapNotes(dbNotesNotInTrash,colorDbModels)
    }

    override fun getNote(id: Long): Flow<NoteModel> = noteDao.findById(id)
        .map {
            val colorDbModel = colorDao.findByIdSync(it.colorId)
            dbMapper.mapNote(it,colorDbModel)
        }


    override suspend fun insertNote(noteModel: NoteModel) {
        noteDao.insert(dbMapper.mapDbNote(noteModel))
        updateNotesLiveData()
    }

    override suspend fun deleteNote(id: Long) {
        noteDao.delete(id)
        updateNotesLiveData()
    }

    override suspend fun deleteNotes(notesIds: List<Long>) {
        noteDao.delete(notesIds)
        updateNotesLiveData()
    }

    override suspend fun moveNoteToTrash(noteId: Long) {
        val dbNote = noteDao.findByIdSync(noteId)
        val newDbNote = dbNote.copy(isInTrash = true)
        noteDao.insert(newDbNote)
        updateNotesLiveData()
    }

    override suspend fun restoreNotesFromTrash(noteIds: List<Long>) {
        val dbNotesInTrash = noteDao.getNotesByIdSync(noteIds = noteIds)
        dbNotesInTrash.forEach {
            val newDbNote = it.copy(isInTrash = false)
            noteDao.insert(newDbNote)
        }
        updateNotesLiveData()
    }

    override fun getAllColors(): Flow<List<ColorModel>> = colorDao.getAll().map {
        dbMapper.mapColors(it)
    }

    override suspend fun getAllColorsSync(): List<ColorModel> =
        dbMapper.mapColors(colorDao.getAllSync())

    override fun getColor(id: Long): Flow<ColorModel> = colorDao.findById(id)
        .map { dbMapper.mapColor(it) }

    override suspend fun getColorSync(id: Long): ColorModel =
        dbMapper.mapColor(colorDao.findByIdSync(id))

    private suspend fun updateNotesLiveData() {
        notesNotInTrashLiveData.update { getAllNotesDependingOnTrashStateSync(inTrash = false) }
        notesInTrashLiveData.update { getAllNotesDependingOnTrashStateSync(inTrash = true) }
    }
}