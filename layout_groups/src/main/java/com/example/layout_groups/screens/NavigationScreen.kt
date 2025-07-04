package com.example.layout_groups.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.layout_groups.R
import com.example.layout_groups.router.FundamentalsRouter
import com.example.layout_groups.router.Screen

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
            NavigationButton(stringResource(id = R.string.row),Screen.Row)
            NavigationButton(stringResource(id= R.string.column),Screen.Column)
            NavigationButton(stringResource(id= R.string.box),Screen.Box)
            NavigationButton(stringResource(id = R.string.surface),Screen.Surface)
            NavigationButton(stringResource(id = R.string.scaffold),Screen.Scaffold)
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
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.colorPrimary)),
        onClick = {FundamentalsRouter.navigateTo(screen)}
    ) {
        Text(
            text = text,
            color = Color.White
        )
    }
}