package com.chocolate.presentation.screens.createorganization

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.createOrganizationRoute() {
    composable(Screen.CreateOrganization.route) {
        CreateOrganizationScreen()
    }
}

fun NavController.navigateToCreateOrganization(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(Screen.CreateOrganization.route, builder = builder)
}