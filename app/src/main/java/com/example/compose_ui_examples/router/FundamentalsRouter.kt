package com.example.compose_ui_examples.router

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {
    data object Navigation: Screen()
    data object Text: Screen()
    data object TextField: Screen()
    data object Buttons: Screen()
    data object ProgressIndicator: Screen()
    data object AlertDialog: Screen()
}

data object FundamentalsRouter {
    var currentState: MutableState<Screen> = mutableStateOf(Screen.Navigation)

    fun navigateTo(destination: Screen) {
        currentState.value = destination
    }
}