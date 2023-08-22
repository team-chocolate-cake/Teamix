package com.chocolate.presentation.screens.direct_message

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.screens.bottomNavigation.BottomNavigationItem

fun NavGraphBuilder.directMessageRoute(navController: NavController){
    composable(BottomNavigationItem.DMs.screen_route){
        DirectMessageScreen(navController = navController)
    }
}
fun NavController.navigateToSearch(){
    navigate(BottomNavigationItem.DMs.screen_route)
}