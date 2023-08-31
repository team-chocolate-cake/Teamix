package com.chocolate.presentation.screens.direct_message

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.screens.create_channel.composable.ActionSnakeBar
import com.chocolate.presentation.util.LocalNavController

@Composable
fun DirectMessageScreen() {
    val navController = LocalNavController.current
    DirectMessageContent()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DirectMessageContent() {
    val context = LocalContext.current
    TeamixScaffold(isDarkMode = isSystemInDarkTheme()) {
        ActionSnakeBar(
            contentMessage = "Direct Message Screen",
            isVisible = true,
            isToggleButtonVisible = false
        )
    }
}
