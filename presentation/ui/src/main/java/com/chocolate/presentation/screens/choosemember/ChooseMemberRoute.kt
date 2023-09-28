package com.chocolate.presentation.screens.choosemember

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chocolate.presentation.Screen
import com.chocolate.viewmodel.createchannel.CreateChannelArgs

fun NavGraphBuilder.chooseMemberRoute() {
    composable(
        route = Screen.ChooseMembers.route +
                "/{${CreateChannelArgs.CHANNEL_NAME}}" +
                "/{${CreateChannelArgs.DESCRIPTION}}",
        arguments = listOf(
            navArgument(CreateChannelArgs.CHANNEL_NAME) { NavType.StringType },
            navArgument(CreateChannelArgs.DESCRIPTION) { NavType.StringType }
        )
    ) {
        ChooseMemberScreen()
    }
}

fun NavController.navigateToChooseMember(
    channelName: String,
    description: String?,
) {
    navigate("${Screen.ChooseMembers.route}/$channelName/$description")
}