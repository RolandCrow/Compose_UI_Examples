package com.example.compose_ui_examples.screens

import androidx.annotation.ColorRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.compose_ui_examples.R


@Composable
fun MyRadioGroup() {
    val radioButtons = listOf(0,1,2)
    val selectButton = remember { mutableIntStateOf(radioButtons.first()) }

    Column {
        radioButtons.forEach { index ->
            val isSelected = index == selectButton.intValue
            val colors = RadioButtonDefaults.colors(
                selectedColor = colorResource(id = R.color.colorPrimary),
                unselectedColor = colorResource(id = R.color.colorPrimaryDark),
                disabledUnselectedColor = Color.LightGray
            )
            RadioButton(
                colors = colors,
                selected = isSelected,
                onClick = {selectButton.intValue = index}
            )
        }
    }
}

@Composable
fun MyFloatingActionButton() {
    FloatingActionButton(
        onClick = {},
        containerColor = colorResource(R.color.colorPrimary),
        contentColor = Color.White,
        content = {
            Icon(Icons.Default.Favorite, contentDescription = "Test FAB")
        }
    )
}