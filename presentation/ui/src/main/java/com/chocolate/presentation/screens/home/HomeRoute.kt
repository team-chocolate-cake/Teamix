package com.chocolate.presentation.screens.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen
import com.chocolate.presentation.screens.main_screen.BottomNavigationItem
import com.chocolate.viewmodel.main.MainViewModel

fun NavGraphBuilder.homeRoute(navController: NavController,mainViewModel: MainViewModel) {
    composable(BottomNavigationItem.Home.screen_route) {
        HomeScreen(navController,mainViewModel)
    }
}

fun NavController.navigateToHome() {
    navigate(Screen.Home.route)
}