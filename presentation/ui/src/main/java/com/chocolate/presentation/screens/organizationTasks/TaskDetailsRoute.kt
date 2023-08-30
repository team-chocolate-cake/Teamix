package com.chocolate.presentation.screens.organizationTasks

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen
import com.chocolate.presentation.screens.bottomNavigation.BottomNavigationItem

fun NavGraphBuilder.taskDetailsRoute(){
    composable(Screen.TaskDetails.route){
        TaskDetailsScreen()
    }
}
fun NavController.navigateToTaskDetailsRoute(){
    navigate(Screen.TaskDetails.route)
}