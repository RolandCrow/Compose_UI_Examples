package com.example.layout_groups.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.layout_groups.R
import com.example.layout_groups.router.BackButton
import com.example.layout_groups.router.FundamentalsRouter
import com.example.layout_groups.router.Screen

@Composable
fun SurfaceScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        MySurface(modifier = modifier.align(Alignment.Center))
    }
    BackButton {
        FundamentalsRouter.navigateTo(Screen.Navigation)
    }
}

@Composable
fun MySurface(modifier: Modifier) {
    Surface(
        modifier = modifier.size(100.dp),
        color = Color.LightGray,
        contentColor = colorResource(id = R.color.colorPrimary),
        border = BorderStroke(1.dp,Color.Black),
        shadowElevation = 1.dp
    ) {
        MyColumn()
    }
}