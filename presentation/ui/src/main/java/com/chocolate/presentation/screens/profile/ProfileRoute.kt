package com.chocolate.presentation.screens.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.profileRoute() {
    composable(Screen.Profile.route) {
        ProfileScreen()
    }
}

fun NavController.navigateToProfile(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(Screen.Profile.route, builder)
}