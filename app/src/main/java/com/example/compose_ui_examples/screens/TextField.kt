package com.example.compose_ui_examples.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.compose_ui_examples.R
import com.example.compose_ui_examples.router.BackButtonHandler
import com.example.compose_ui_examples.router.FundamentalsRouter
import com.example.compose_ui_examples.router.Screen

@Composable
fun TextFieldScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        MyTextField()
    }

    BackButtonHandler {
        FundamentalsRouter.navigateTo(Screen.Navigation)
    }
}


@Composable
fun MyTextField() {
    val textValue = remember { mutableStateOf("") }
    val primaryColor = colorResource(id= R.color.colorPrimary)

    OutlinedTextField(
        value = textValue.value,
        onValueChange = { textValue.value = it },
        label = {Text(text = stringResource(R.string.email))},
        colors = TextFieldDefaults.colors(focusedLabelColor = primaryColor, cursorColor = primaryColor),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
    )
}