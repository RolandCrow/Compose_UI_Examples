package com.example.compose_ui_examples.screens

import android.app.AlertDialog
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.compose_ui_examples.R
import com.example.compose_ui_examples.router.BackButtonHandler
import com.example.compose_ui_examples.router.FundamentalsRouter
import com.example.compose_ui_examples.router.Screen

@Composable
fun AlertDialogScreen() {
    MyAlertDialog()
    BackButtonHandler {
        FundamentalsRouter.navigateTo(Screen.Navigation)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAlertDialog() {
    val shouldShowDialog = remember { mutableStateOf(true) }
    when(shouldShowDialog.value) {
        true -> {
            BasicAlertDialog(
                onDismissRequest = {
                    shouldShowDialog.value = false
                    FundamentalsRouter.navigateTo(Screen.Navigation)
                },
                content = {
                  Button(
                      colors = ButtonDefaults.buttonColors(
                          containerColor = colorResource(R.color.colorPrimary)
                      ),
                      onClick = {
                          shouldShowDialog.value = false
                          FundamentalsRouter.navigateTo(Screen.Navigation)
                      }
                  ) {
                      Text(
                          text = stringResource(R.string.confirm),
                          color = Color.White
                      )
                  }
                })
        }
        else -> {}
    }
}