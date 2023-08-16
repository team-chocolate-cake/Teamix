package com.chocolate.presentation.screens.oner_power

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.ownerPowerRoute(navController: NavController){
    composable(Screen.OwnerPower.route){
        OwnerPowerScreen(navController)
    }
}
fun NavController.navigateToOwnerPower(){
    navigate(Screen.OwnerPower.route)
}