package com.chocolate.presentation.screens.createmember

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chocolate.presentation.Screen
import com.chocolate.viewmodel.choosemember.CreateMemberArgs

fun NavGraphBuilder.createMemberRoute() {
    composable(
        route = "${Screen.CreateMember.route}/{${CreateMemberArgs.ROLE}}",
        arguments = listOf(navArgument(CreateMemberArgs.ROLE) { NavType.StringType })
    ) {
        CreateMemberScreen()
    }
}
fun NavController.navigateToCreateMember(role: String){
    navigate("${Screen.CreateMember.route}/$role")
}