package com.chocolate.presentation.screens.taskorganization

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.screens.bottomnavigation.BottomNavigationItem

fun NavGraphBuilder.taskOrganizationRoute(){
    composable(BottomNavigationItem.TasksOrganization.screenRoute){
        TaskOrganizationScreen()
    }
}
fun NavController.navigateToTaskOrganization(){
    navigate(BottomNavigationItem.TasksOrganization.screenRoute)
}