package com.chocolate.presentation.screens.direct_message

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.direct_messages.specific_dm.DirectMessageInteraction
import com.chocolate.viewmodel.direct_messages.specific_dm.DirectMessageUiState
import com.chocolate.viewmodel.direct_messages.specific_dm.DirectMessageViewModel

@Composable
fun DirectMessageScreen(
    viewModel: DirectMessageViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val state by viewModel.state.collectAsState()
    DirectMessageContent(state, viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DirectMessageContent(state: DirectMessageUiState, interaction: DirectMessageInteraction) {
    val context = LocalContext.current
    TeamixScaffold(isDarkMode = isSystemInDarkTheme()) {
        Toast.makeText(context, "Direct Message Screen", Toast.LENGTH_SHORT).show()
    }
}