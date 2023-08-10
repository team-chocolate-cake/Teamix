package com.chocolate.presentation.screens.organiztion

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.organizationNameRoute(navController: NavController) {
    composable(Screen.OrganizationName.route) {
        OrganizationScreen(navController)
    }
}

fun NavController.navigateToOrganizationName() {
    navigate(Screen.OrganizationName.route)
}


