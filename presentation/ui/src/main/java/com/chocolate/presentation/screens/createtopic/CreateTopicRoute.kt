package com.chocolate.presentation.screens.createtopic

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chocolate.presentation.Screen
import com.chocolate.viewmodel.createtopic.CreateTopicArgs


fun NavGraphBuilder.createTopicRoute() {
    composable(
        route = "${Screen.CreateTopic.route}/{${CreateTopicArgs.CHANNEL_Id}}",
        arguments = listOf(navArgument(CreateTopicArgs.CHANNEL_Id) { NavType.StringType })
    ) {
        CreateTopicScreen()
    }
}

fun NavController.navigateToCreateTopic(channelId: String) {
    navigate("${Screen.CreateTopic.route}/$channelId")
}