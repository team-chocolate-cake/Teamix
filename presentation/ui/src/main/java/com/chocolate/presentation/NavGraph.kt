package com.chocolate.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.chocolate.presentation.on_boarding.onboardingRoute
import com.chocolate.presentation.screens.profile.ownerPowerRoute
import com.chocolate.presentation.screens.profile.profileRoute
import com.chocolate.presentation.welcome.welcomeRoute

@Composable
fun SetUpNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Profile.route) {
        welcomeRoute(navController)
        onboardingRoute(navController)
        profileRoute(navController)
        ownerPowerRoute(navController)

    }
}