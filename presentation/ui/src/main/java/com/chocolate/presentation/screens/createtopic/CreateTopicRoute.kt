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
        route = "${Screen.CreateTopic.route}/{${CreateTopicArgs.CHANNEL_NAME}}",
        arguments = listOf(navArgument(CreateTopicArgs.CHANNEL_NAME) { NavType.StringType })
    ) {
        CreateTopicScreen()
    }
}

fun NavController.navigateToCreateTopic(channelName: String) {
    navigate("${Screen.CreateTopic.route}/$channelName")
}