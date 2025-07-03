package com.example.layout_groups.router

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {
    data object Navigation: Screen()
    data object Row: Screen()
    data object Column: Screen()
    data object Box: Screen()
    data object Surface: Screen()
    data object Scaffold: Screen()
}

object FundamentalsRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.Navigation)
    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }
}