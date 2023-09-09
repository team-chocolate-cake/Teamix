package com.chocolate.presentation.screens.createMember

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.createMemberRoute() {
    composable(
        route = Screen.CreateMember.route,
    ) {
        CreateMemberScreen()
    }
}
fun NavController.navigateToCreateMember(){
    navigate(Screen.CreateMember.route)
}