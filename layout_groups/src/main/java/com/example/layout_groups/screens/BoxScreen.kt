package com.example.layout_groups.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.layout_groups.R
import com.example.layout_groups.router.BackButton
import com.example.layout_groups.router.FundamentalsRouter
import com.example.layout_groups.router.Screen

@Composable
fun BoxScreen() {
    MyBox()

    BackButton {
        FundamentalsRouter.navigateTo(Screen.Navigation)
    }
}

@Composable
fun MyBox(
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.first),
            fontSize = 22.sp,
            modifier = contentModifier.align(Alignment.TopStart),
        )
        Text(
            text = stringResource(R.string.second),
            fontSize = 22.sp,
            modifier = contentModifier.align(Alignment.Center)
        )
        Text(
            text = stringResource(R.string.third),
            fontSize = 22.sp,
            modifier = contentModifier.align(Alignment.BottomEnd)
        )
    }
}