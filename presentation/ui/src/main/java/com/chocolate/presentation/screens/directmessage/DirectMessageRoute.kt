package com.chocolate.presentation.screens.directmessage

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.screens.bottomnavigation.BottomNavigationItem

fun NavGraphBuilder.directMessageRoute(){
    composable(BottomNavigationItem.DMs.screenRoute){
        DirectMessageScreen()
    }
}
fun NavController.navigateToSearch(){
    navigate(BottomNavigationItem.DMs.screenRoute)
}