package com.example.compose_ui_examples.app

import androidx.compose.animation.Crossfade
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Surface
import com.example.compose_ui_examples.router.FundamentalsRouter
import com.example.compose_ui_examples.router.Screen
import com.example.compose_ui_examples.screens.AlertDialogScreen
import com.example.compose_ui_examples.screens.ExploreButtonsScreen
import com.example.compose_ui_examples.screens.NavigationScreen
import com.example.compose_ui_examples.screens.ProgressIndicatorScreen
import com.example.compose_ui_examples.screens.TextFieldScreen
import com.example.compose_ui_examples.screens.TextScreen


@Composable
fun FundamentalsApp() {
    Surface(color =MaterialTheme.colorScheme.background) {
        Crossfade(targetState = FundamentalsRouter.currentState) { screenState ->
            when(screenState.value) {
                is Screen.Navigation -> NavigationScreen()
                is Screen.Text -> TextScreen()
                is Screen.TextField -> TextFieldScreen()
                is Screen.Buttons -> ExploreButtonsScreen()
                is Screen.ProgressIndicator -> ProgressIndicatorScreen()
                is Screen.AlertDialog -> AlertDialogScreen()
            }
        }
    }
}