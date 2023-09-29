package com.chocolate.presentation.screens.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.profileRoute() {
    composable(Screen.Profile.route) {
        ProfileScreen()
    }
}