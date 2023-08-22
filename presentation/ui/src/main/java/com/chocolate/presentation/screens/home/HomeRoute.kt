package com.chocolate.presentation.screens.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen
import com.chocolate.presentation.screens.bottomNavigation.BottomNavigationItem
import com.chocolate.viewmodel.main.MainViewModel

fun NavGraphBuilder.homeRoute(navController: NavController,mainViewModel: MainViewModel) {
    composable(BottomNavigationItem.Home.screen_route) {
        HomeScreen(navController,mainViewModel)
    }
}

fun NavController.navigateToHome() {
    popBackStack()
    navigate(Screen.Home.route){
        popUpTo(Screen.OrganizationName.route){
            inclusive = true
        }
    }
}