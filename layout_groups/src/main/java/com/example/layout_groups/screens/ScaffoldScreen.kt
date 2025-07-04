package com.example.layout_groups.screens

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.layout_groups.R
import com.example.layout_groups.router.BackButton
import com.example.layout_groups.router.FundamentalsRouter
import com.example.layout_groups.router.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ScaffoldScreen() {
    MyScaffold()
    BackButton {
        FundamentalsRouter.navigateTo(Screen.Navigation)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyScaffold() {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        contentColor = colorResource(id = R.color.colorPrimary),
        content =  {MyRow()},
        topBar = { MyTopAppBar(scaffoldState = scaffoldState, scope = scope) },
        bottomBar = { MyBottomAppBar() },
        drawerContent = { MyColumn() }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(scaffoldState: ScaffoldState, scope: CoroutineScope) {
    val drawerState = scaffoldState.drawerState
    TopAppBar(
        navigationIcon = {
            IconButton(
                content = {
                    Icon(
                        Icons.Default.Menu,
                        tint = Color.White,
                        contentDescription = stringResource(R.string.menu)
                    )
                },
                onClick = {
                    scope.launch { if(drawerState.isClosed) drawerState.open() else drawerState.close()  }
                }
            )
        },
        title = {
            Text(text = stringResource(R.string.app_name), color = Color.White)
        },
        colors =  TopAppBarDefaults.topAppBarColors(containerColor = colorResource(R.color.colorPrimary))
    )
}

@Composable
fun MyBottomAppBar() {
    BottomAppBar(
        content = {},
        containerColor = colorResource(id = R.color.colorPrimary)
    )
}