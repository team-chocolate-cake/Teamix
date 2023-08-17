package com.chocolate.presentation.screens.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen
import com.chocolate.viewmodel.main.MainViewModel

fun NavGraphBuilder.profileRoute(navController: NavController,mainViewModel: MainViewModel){
  composable(Screen.Profile.route){
      ProfileScreen(navController,mainViewModel)
  }
}
fun NavController.navigateToProfile(){
    navigate(Screen.Profile.route)
}