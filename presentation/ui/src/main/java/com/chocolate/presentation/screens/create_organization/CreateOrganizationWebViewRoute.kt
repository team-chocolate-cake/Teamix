package com.chocolate.presentation.screens.create_organization

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.createOrganizationWebViewRoute(navController: NavController) {
    composable(Screen.OrganizationWebView.route) {
        CreateOrganizationWebViewScreen(navController)
    }
}
fun NavController.navigateToCreateOrganization(){
    navigate(Screen.OrganizationWebView.route)
}