package com.chocolate.presentation.screens.taskOrganiztion

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.screens.add_task.navigateToAddTask
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.taskOrganization.TaskOrganizationUiState
import com.chocolate.viewmodel.taskOrganization.TaskOrganizationViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TaskOrganizationScreen(viewModel: TaskOrganizationViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    val state by viewModel.state.collectAsState()
    TaskOrganizationContent(state, navController::navigateToAddTask)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskOrganizationContent(
    state: TaskOrganizationUiState,
    navigateToAddTask: () -> Unit,
) {
    TeamixScaffold(isDarkMode = isSystemInDarkTheme()) {
        val colors = MaterialTheme.customColors()
        val systemUiController = rememberSystemUiController()
        systemUiController.setStatusBarColor(
            color = colors.background,
            darkIcons = !state.isDarkMode
        )

        EmptyTasks(
            onClickCreateTask = navigateToAddTask
        )

    }
}
