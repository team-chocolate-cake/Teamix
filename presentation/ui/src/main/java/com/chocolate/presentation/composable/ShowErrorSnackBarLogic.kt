package com.chocolate.presentation.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import com.chocolate.presentation.screens.create_channel.composable.ActionSnakeBar
import kotlinx.coroutines.delay

@Composable
fun ShowErrorSnackBarLogic(
    showSnackBar: MutableState<Boolean>,
    contentMessage:String
) {
    if (showSnackBar.value) {
        ActionSnakeBar(
            contentMessage = contentMessage,
            isVisible = true,
            isToggleButtonVisible = false
        )
    }
    LaunchedEffect(showSnackBar.value) {
        if (showSnackBar.value) {
            delay(2000)
            showSnackBar.value = false
        }
    }
}