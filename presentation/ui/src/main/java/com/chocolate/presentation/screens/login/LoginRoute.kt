package com.chocolate.presentation.screens.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chocolate.presentation.Screen
import com.chocolate.presentation.screens.organiztion.OrganizationScreen
import com.chocolate.viewmodel.login.LoginArgs

fun NavGraphBuilder.loginRoute(navController: NavController) {
    composable(
        route = "${Screen.Login.route}/{${LoginArgs.ORGANIZATION_NAME}}",
        arguments = listOf(navArgument(LoginArgs.ORGANIZATION_NAME) { NavType.StringType })
    ) {
        LoginScreen(navController = navController)
    }
}

fun NavController.navigateToLogin(organizationName: String) {
    popBackStack()
    navigate("${Screen.Login.route}/$organizationName")
}

fun NavController.backToLogin(){
    popBackStack()
}
