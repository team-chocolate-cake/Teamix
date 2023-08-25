package com.chocolate.presentation.screens.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen
import com.chocolate.presentation.screens.bottomNavigation.BottomNavigationItem

fun NavGraphBuilder.homeRoute() {
    composable(BottomNavigationItem.Home.screenRoute) {
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