package com.chocolate.presentation.screens.taskorganization

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.screens.createchannel.composable.ActionSnakeBar
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.taskorganization.TaskOrganizationUiState
import com.chocolate.viewmodel.taskorganization.TaskOrganizationViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TaskOrganizationScreen(viewModel: TaskOrganizationViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    val state by viewModel.state.collectAsState()
    TaskOrganizationContent(state)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskOrganizationContent(state: TaskOrganizationUiState) {
    TeamixScaffold(isDarkMode = isSystemInDarkTheme()) {
        val colors = MaterialTheme.customColors()
        val systemUiController = rememberSystemUiController()
        systemUiController.setStatusBarColor(color = colors.background, darkIcons = !state.isDarkMode)

        ActionSnakeBar(
            contentMessage = "Task Screen",
            isVisible = true,
            isToggleButtonVisible = false
        )

    }
}
