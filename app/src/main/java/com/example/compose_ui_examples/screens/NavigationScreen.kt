package com.example.compose_ui_examples.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.compose_ui_examples.R
import com.example.compose_ui_examples.router.FundamentalsRouter
import com.example.compose_ui_examples.router.Screen

@Composable
fun NavigationScreen() {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationButton(text = stringResource(R.string.text), Screen.Text)
            NavigationButton(text = stringResource(R.string.text_field), Screen.TextField)
            NavigationButton(text = stringResource(R.string.buttons), Screen.Buttons)
            NavigationButton(text = stringResource(R.string.progress_indicators), Screen.ProgressIndicator)
            NavigationButton(text = stringResource(R.string.alert_dialog), Screen.AlertDialog)
        }
    }
}

@Composable
fun NavigationButton(text: String, screen: Screen) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(contentColor = colorResource(R.color.colorPrimary)),
        onClick = {FundamentalsRouter.navigateTo(screen)}
    ) {
        Text(
            text = text,
            color = Color.White
        )
    }
}