package com.example.lists.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.lists.R
import com.example.lists.router.BackButton
import com.example.lists.router.FundamentalsRouter
import com.example.lists.router.Screen

@Composable
fun ScrollingScreen() {
    MyScrollingScreen()
    BackButton {
        FundamentalsRouter.navigateTo(Screen.Navigation)
    }
}

@Composable
fun MyScrollingScreen(modifier: Modifier = Modifier) {
    Row(modifier = modifier.horizontalScroll(rememberScrollState())) {
        BookImage(R.drawable.advanced_architecture_android, R.string.advanced_architecture_android)
        BookImage(R.drawable.kotlin_aprentice, R.string.kotlin_apprentice)
        BookImage(R.drawable.kotlin_coroutines, R.string.kotlin_coroutines)
    }
}

@Composable
fun BookImage(@DrawableRes imageResId: Int, @StringRes contentDescriptionResId: Int) {
    Image(
        bitmap = ImageBitmap.imageResource(imageResId),
        contentDescription = stringResource(contentDescriptionResId),
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.size(476.dp,616.dp)
    )
}