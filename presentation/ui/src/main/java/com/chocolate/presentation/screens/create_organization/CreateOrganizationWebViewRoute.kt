package com.chocolate.presentation.screens.create_organization

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.createOrganizationWebViewRoute() {
    composable(Screen.OrganizationWebView.route) {
        CreateOrganizationWebViewScreen()
    }
}
fun NavController.navigateToCreateOrganization(){
    navigate(Screen.OrganizationWebView.route)
}