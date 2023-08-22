package com.chocolate.presentation.screens.task

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.screens.main_screen.BottomNavigationItem
import com.chocolate.presentation.screens.search.SearchScreen

fun NavGraphBuilder.taskRoute(){
    composable(BottomNavigationItem.Tasks.screen_route){
        TaskScreen()
    }
}
fun NavController.navigateToTask(){
    navigate(BottomNavigationItem.Tasks.screen_route)
}