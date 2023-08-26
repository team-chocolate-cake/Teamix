package com.chocolate.presentation.screens.channel

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chocolate.presentation.Screen
import com.chocolate.viewmodel.channel.ChannelArgs

fun NavGraphBuilder.channelRoute() {
    composable(
        route = "${Screen.Channel.route}/{${ChannelArgs.CHANNEL_ID}}/{${ChannelArgs.CHANNEL_NAME}}",
        arguments = listOf(
            navArgument(ChannelArgs.CHANNEL_ID) { NavType.IntType },
            navArgument(ChannelArgs.CHANNEL_NAME) { NavType.StringType }
        )
    ) {
        ChannelScreen()
    }
}

fun NavController.toChannelScreen(id: Int, name: String) {
    navigate("${Screen.Channel.route}/$id/$name")
}