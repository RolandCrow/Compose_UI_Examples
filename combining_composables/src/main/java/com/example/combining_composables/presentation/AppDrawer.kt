package com.example.combining_composables.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.combining_composables.routing.Screen
import com.example.combining_composables.ui.theme.Compose_UI_ExamplesThemeSettings

@Composable
fun AppDrawer(
    currentScreen: Screen,
    onScreenSelected: (Screen) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppDrawerHeader()
        HorizontalDivider(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 2f)
        )
        ScreenNavigationButton(
            icon = Icons.Filled.Home,
            label = "notes",
            isSelected = currentScreen == Screen.Notes,
            onClick = {
                onScreenSelected.invoke(Screen.Notes)
            }
        )
        ScreenNavigationButton(
            icon = Icons.Filled.Delete,
            label = "Trash",
            isSelected = currentScreen == Screen.Trash,
            onClick = {
                onScreenSelected.invoke(Screen.Trash)
            }
        )
        LightDarkThemeItem()
    }
}

@Composable
private fun AppDrawerHeader() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            imageVector = Icons.Filled.Menu,
            contentDescription = "Drawer Header Icon",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "JetNotes",
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
        )
    }
}

@Composable
private fun ScreenNavigationButton(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: ()-> Unit
) {
    val colors = MaterialTheme.colorScheme
    val imageAlpha = if(isSelected) {
        1f
    } else {
        0.6f
    }
    val textColor = if(isSelected) {
        colors.primary
    } else {
        colors.onSurface
    }

    val backgroundColor = if(isSelected) {
        colors.primary.copy(alpha = 0.12f)
    } else {
        colors.surface
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
        color = backgroundColor,
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(onClick = onClick)
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Image(
              imageVector = icon,
              contentDescription = "Screen Navigation Button",
              colorFilter = ColorFilter.tint(textColor),
               alpha = imageAlpha
            )
            Spacer(Modifier.width(16.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = textColor,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
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