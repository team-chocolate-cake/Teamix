package com.chocolate.presentation.screens.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.homeRoute(navController: NavController) {
    composable(Screen.Home.route) {
        HomeScreen(navController)
    }
}

fun NavController.navigateToHome() {
    navigate(Screen.Home.route)
}