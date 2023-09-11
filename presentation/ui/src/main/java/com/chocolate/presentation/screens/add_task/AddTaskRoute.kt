package com.chocolate.presentation.screens.add_task

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.addTaskRoute() {
    composable(Screen.AddTask.route) {
        AddTaskScreen()
    }
}

fun NavController.navigateToAddTask() {
    navigate(Screen.AddTask.route)
}