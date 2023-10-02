package com.chocolate.presentation.screens.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.homeRoute() {
    composable(Screen.Home.route) {
        HomeScreen()
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