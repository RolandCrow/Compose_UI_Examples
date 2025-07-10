package com.example.combining_composables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.combining_composables.routing.Screen
import com.example.combining_composables.ui.theme.Compose_UI_ExamplesTheme
import com.example.combining_composables.presentation.AppDrawer
import com.example.combining_composables.presentation.NotesScreen
import com.example.combining_composables.viewmodel.MainViewModel
import com.example.combining_composables.viewmodel.MainViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels(factoryProducer = {
        MainViewModelFactory(
            this,
            (application as JetNotesApplication).di.repository)
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Compose_UI_Examples)
        setContent {
            Compose_UI_ExamplesTheme {
                val coroutineScope = rememberCoroutineScope()
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val navController = rememberNavController()

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        AppDrawer(
                           currentScreen = Screen.Notes,
                            onScreenSelected = { screen ->
                                coroutineScope.launch {
                                    drawerState.close()
                                }
                            }
                        )
                    },
                    content = {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.Notes.route
                        ) {
                            composable(Screen.Notes.route) { NotesScreen(viewModel) }
                        }
                    }
                )
            }
        }
    }
}