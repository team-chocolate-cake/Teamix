package com.chocolate.presentation.screens.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.screens.bottomNavigation.BottomNavigationItem

fun NavGraphBuilder.searchRoute(){
    composable(BottomNavigationItem.Search.screenRoute){
        SearchScreen()
    }
}
fun NavController.navigateToSearch(){
    navigate(BottomNavigationItem.Search.screenRoute)
}