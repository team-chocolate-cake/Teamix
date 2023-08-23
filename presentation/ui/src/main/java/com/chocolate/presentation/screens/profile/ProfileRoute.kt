package com.chocolate.presentation.screens.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen
import com.chocolate.viewmodel.main.MainViewModel

fun NavGraphBuilder.profileRoute(mainViewModel: MainViewModel) {
    composable(Screen.Profile.route) {
        ProfileScreen(mainViewModel)
    }
}

fun NavController.navigateToProfile(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(Screen.Profile.route, builder)
}