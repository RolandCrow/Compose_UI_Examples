package com.example.layout_groups.app

import androidx.compose.animation.Crossfade
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.example.layout_groups.router.FundamentalsRouter
import com.example.layout_groups.router.Screen
import com.example.layout_groups.screens.BoxScreen
import com.example.layout_groups.screens.ColumnScreen
import com.example.layout_groups.screens.NavigationScreen
import com.example.layout_groups.screens.RowScreen
import com.example.layout_groups.screens.ScaffoldScreen
import com.example.layout_groups.screens.SurfaceScreen

@Composable
fun FundamentalsApp() {
    Surface(color = MaterialTheme.colorScheme.background) {
        Crossfade(targetState = FundamentalsRouter.currentScreen) { screenState ->
            when(screenState.value) {
                is Screen.Row -> RowScreen()
                is Screen.Column -> ColumnScreen()
                is Screen.Box -> BoxScreen()
                is Screen.Surface -> SurfaceScreen()
                is Screen.Scaffold -> ScaffoldScreen()
                is Screen.Navigation -> NavigationScreen()
            }
        }
    }
}