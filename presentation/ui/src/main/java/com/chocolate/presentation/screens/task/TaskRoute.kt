package com.chocolate.presentation.screens.task

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.screens.bottomNavigation.BottomNavigationItem

fun NavGraphBuilder.taskRoute(){
    composable(BottomNavigationItem.Tasks.screenRoute){
        TaskScreen()
    }
}
fun NavController.navigateToTask(){
    navigate(BottomNavigationItem.Tasks.screenRoute)
}