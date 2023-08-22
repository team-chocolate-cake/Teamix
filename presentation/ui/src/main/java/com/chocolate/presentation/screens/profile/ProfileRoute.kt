package com.chocolate.presentation.screens.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen
import com.chocolate.viewmodel.main.MainViewModel

fun NavGraphBuilder.profileRoute(mainViewModel: MainViewModel){
  composable(Screen.Profile.route){
      ProfileScreen(mainViewModel)
  }
}
fun NavController.navigateToProfile(){
    navigate(Screen.Profile.route){
        launchSingleTop = true
    }
}