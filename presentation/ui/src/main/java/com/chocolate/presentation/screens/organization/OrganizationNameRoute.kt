package com.chocolate.presentation.screens.organization

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.organizationNameRoute() {
    composable(Screen.OrganizationName.route) {
        OrganizationScreen()
    }
}

fun NavController.navigateToOrganizationName(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(Screen.OrganizationName.route, builder = builder)
}