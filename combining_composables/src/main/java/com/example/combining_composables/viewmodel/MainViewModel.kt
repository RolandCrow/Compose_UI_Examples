package com.example.combining_composables.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.combining_composables.domain.ColorModel
import com.example.combining_composables.domain.NoteModel
import com.example.combining_composables.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: Repository): ViewModel() {
    val notesNotInTrash: LiveData<List<NoteModel>> by lazy {
        repository.getAllNotesNotInTrash().asLiveData()
    }
    private val _noteEntry = MutableStateFlow(NoteModel())
    val noteEntry: LiveData<NoteModel> = _noteEntry.asLiveData()

    val colors: LiveData<List<ColorModel>> by lazy {
        repository.getAllColors().asLiveData()
    }
    val notesInTrash: LiveData<List<NoteModel>> by lazy {
        repository.getAllNotesInTrash().asLiveData()
    }

    private var _selectedNotes = MutableStateFlow<List<NoteModel>>(listOf())
    val selectedNotes: LiveData<List<NoteModel>> = _selectedNotes.asLiveData()

    fun onCreateNewNoteClick() {
        _noteEntry.value = NoteModel()
    }

    fun onNoteClick(note: NoteModel) {
        _noteEntry.value = note
    }

    fun onNoteCheckedChange(noteModel: NoteModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.insertNote(noteModel)
        }
    }

    fun onNoteSelected(note: NoteModel) {
        _selectedNotes.value = _selectedNotes.value.toMutableList().apply {
            if(contains(note)) {
                remove(note)
            } else {
                add(note)
            }
        }
    }

    fun restoreNotes(notes: List<NoteModel>) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.restoreNotesFromTrash(notes.map { it.id })
            withContext(Dispatchers.Main) {
                _selectedNotes.value = listOf()
            }
        }
    }

    fun permanentlyDeletesNotes(notes:List<NoteModel>) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.deleteNotes(notes.map { it.id })
            withContext(Dispatchers.Main) {
                _selectedNotes.value = listOf()
            }
        }
    }

    fun onNoteEntryChange(note: NoteModel) {
        _noteEntry.value = note
    }

    fun saveNote(note: NoteModel) {
        viewModelScope.launch(Dispatchers.Main) {
            repository.insertNote(note)
            withContext(Dispatchers.Main) {
                _noteEntry.value = NoteModel()
            }
        }
    }

    fun moveNoteToTrash(note: NoteModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.moveNoteToTrash(note.id)
        }
    }
}