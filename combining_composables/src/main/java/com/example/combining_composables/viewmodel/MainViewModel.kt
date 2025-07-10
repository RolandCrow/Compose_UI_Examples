package com.example.combining_composables.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.combining_composables.domain.NoteModel
import com.example.combining_composables.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel() {
    val notesNotInTrash: LiveData<List<NoteModel>> by lazy {
        repository.getAllNotesNotInTrash().asLiveData()
    }

    fun onCreateNewNoteClick() {

    }

    fun onNoteClick(note: NoteModel) {

    }

    fun onNoteCheckedChange(noteModel: NoteModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.insertNote(noteModel)
        }
    }
}