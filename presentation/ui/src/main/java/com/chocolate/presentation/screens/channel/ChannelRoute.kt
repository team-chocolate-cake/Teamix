package com.chocolate.presentation.screens.channel

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chocolate.presentation.Screen
import com.chocolate.presentation.screens.home.HomeScreen
import com.chocolate.viewmodel.channel.ChannelArgs
import com.chocolate.viewmodel.login.LoginArgs

fun NavGraphBuilder.channelRoute() {
    composable(
        route = "${Screen.Channel.route}/{${ChannelArgs.CHANNEL_ID}}",
        arguments = listOf(navArgument(ChannelArgs.CHANNEL_ID) { NavType.IntType })
    ) {
        ChannelScreen()
    }
}

fun NavController.toChannelScreen(id: Int) {
    navigate("${Screen.Channel.route}/$id")
}