package com.example.lists.app

import androidx.compose.animation.Crossfade
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.example.lists.router.FundamentalsRouter
import com.example.lists.router.Screen
import com.example.lists.screens.GridScreen
import com.example.lists.screens.ListScreen
import com.example.lists.screens.NavigationScreen
import com.example.lists.screens.ScrollingScreen

@Composable
fun FundamentalsApp() {
    Surface(color = MaterialTheme.colorScheme.background) {
        Crossfade(targetState = FundamentalsRouter.currentScreen) { screenState ->
            when(screenState.value) {
                is Screen.Navigation -> NavigationScreen()
                is Screen.Scrolling -> ScrollingScreen()
                is Screen.List -> ListScreen()
                is Screen.Grid -> GridScreen()
            }
        }
    }
}