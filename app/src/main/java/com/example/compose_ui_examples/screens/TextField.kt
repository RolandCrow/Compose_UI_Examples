package com.example.compose_ui_examples.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.compose_ui_examples.R

@Composable
fun MyTextField() {
    val textValue = remember { mutableSetOf("") }
    val primaryColor = colorResource(id= R.color.colorPrimary)

}