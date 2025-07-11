package com.example.combining_composables.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.combining_composables.domain.NoteModel
import com.example.combining_composables.viewmodel.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(viewModel: MainViewModel,
                onOpenNavigationDrawer: () -> Unit = {},
                onNavigateToSaveNote: () -> Unit = {}) {
    val notes: List<NoteModel> by viewModel.notesNotInTrash.observeAsState(listOf())
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {},
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "JetNotes", color = MaterialTheme.colorScheme.onPrimary)
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                onOpenNavigationDrawer.invoke()
                            }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.List,
                                    contentDescription = "Drawer Button"
                                )
                            }
                        }
                    )
                },
                floatingActionButtonPosition = FabPosition.End,
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {

                        },
                        contentColor = MaterialTheme.colorScheme.background,
                        content = {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Add Note Button"
                            )
                        }
                    )
                },
                content = {
                    if(notes.isNotEmpty()) {
                        NotesList(
                            notes = notes,
                            onNoteCheckedChange = {
                                viewModel.onNoteCheckedChange(it)
                            },
                            onNoteClick = {
                                viewModel.onNoteClick(it)
                                onNavigateToSaveNote.invoke()
                            },
                        )
                    }
                }
            )
        }
    )
}

@Composable
private fun NotesList(
    notes: List<NoteModel>,
    onNoteCheckedChange: (NoteModel) -> Unit,
    onNoteClick: (NoteModel) -> Unit
) {
    LazyColumn {
        items(count = notes.size) { noteIndex ->
            val note = notes[noteIndex]
            Note(
                note = note,
                onNoteClick = onNoteClick,
                onNoteCheckedChange = onNoteCheckedChange,
                isSelected = false
            )
        }
    }
}