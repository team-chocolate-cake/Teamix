package com.chocolate.presentation.screens.organizationTasks

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.screens.bottomNavigation.BottomNavigationItem

fun NavGraphBuilder.organizationTasksRoute(){
    composable(BottomNavigationItem.Tasks.screenRoute){
        OrganizationTasksScreen()
    }
}
fun NavController.navigateToOrganizationTasks(){
    navigate(BottomNavigationItem.Tasks.screenRoute)
}