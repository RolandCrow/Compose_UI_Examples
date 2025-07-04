package com.example.lists.router

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {
    data object Navigation: Screen()
    data object Scrolling: Screen()
    data object List: Screen()
    data object Grid: Screen()
}

object FundamentalsRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.Navigation)
    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }
}