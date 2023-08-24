package com.chocolate.presentation.screens.create_channel

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.createChannelRout(){
    composable(route = Screen.CreateChannel.route){
        CreateChannelScreen()
    }
}