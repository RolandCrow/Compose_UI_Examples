package com.example.combining_composables.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.combining_composables.ui.theme.Compose_UI_ExamplesThemeSettings

@Composable
private fun ScreenNavigationButton(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: ()-> Unit
) {

}

@Composable
private fun LightDarkThemeItem() {
    Row(
        Modifier.padding(8.dp)
    ) {
        Text(
            text = "Turn on dark theme",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f),
            modifier = Modifier.weight(1f).padding(
                start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp
            ).align(alignment = Alignment.CenterVertically)
        )
        Switch(
            checked = Compose_UI_ExamplesThemeSettings.isDarkThemeEnabled,
            onCheckedChange = {
                Compose_UI_ExamplesThemeSettings.isDarkThemeEnabled = it
            },
            modifier = Modifier.padding(start = 8.dp, end = 8.dp).align(alignment = Alignment.CenterVertically)
        )
    }
}