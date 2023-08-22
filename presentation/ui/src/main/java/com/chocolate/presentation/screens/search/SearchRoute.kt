package com.chocolate.presentation.screens.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.screens.bottomNavigation.BottomNavigationItem

fun NavGraphBuilder.searchRoute(navController: NavController){
    composable(BottomNavigationItem.Search.screen_route){
        SearchScreen(navController = navController)
    }
}
fun NavController.navigateToSearch(){
    navigate(BottomNavigationItem.Search.screen_route)
}