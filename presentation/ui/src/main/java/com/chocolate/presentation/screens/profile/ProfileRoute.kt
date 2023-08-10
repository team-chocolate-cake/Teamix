package com.chocolate.presentation.screens.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.profileRoute(navController: NavController){
  composable(Screen.Profile.route){
      ProfileScreen(navController)
  }
}
fun NavController.navigateToProfile(){
    navigate(Screen.Profile.route)
}