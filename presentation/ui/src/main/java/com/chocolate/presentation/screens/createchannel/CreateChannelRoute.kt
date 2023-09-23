package com.chocolate.presentation.screens.createchannel

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.createChannelRoute(){
    composable(route = Screen.CreateChannel.route){
        CreateChannelScreen()
    }
}

fun NavController.navigateToCreateChannel(){
    navigate(Screen.CreateChannel.route)
}