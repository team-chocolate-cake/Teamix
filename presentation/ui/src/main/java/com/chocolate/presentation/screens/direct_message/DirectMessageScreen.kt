package com.chocolate.presentation.screens.direct_message

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.screens.create_channel.composable.ActionSnakeBar
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.directMessage.DirectMessageUiState
import com.chocolate.viewmodel.directMessage.DirectMessageViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun DirectMessageScreen(viewModel: DirectMessageViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    val state by viewModel.state.collectAsState()
    DirectMessageContent(state)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DirectMessageContent(state: DirectMessageUiState) {
    val context = LocalContext.current
    TeamixScaffold(isDarkMode = isSystemInDarkTheme()) {
        val systemUiController = rememberSystemUiController()
        systemUiController.setStatusBarColor(
            color = MaterialTheme.customColors().primary,
            darkIcons = false
        )

        ActionSnakeBar(
            contentMessage = "Direct Message Screen",
            isVisible = true,
            isToggleButtonVisible = false
        )
    }
}
