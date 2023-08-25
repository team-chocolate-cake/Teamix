package com.chocolate.presentation.screens.organiztion

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.organizationNameRoute() {
    composable(Screen.OrganizationName.route) {
        OrganizationScreen()
    }
}

fun NavController.navigateToOrganizationName() {
    navigate(Screen.OrganizationName.route){
        popUpTo(Screen.Home.route){
            inclusive = true
        }
    }
}