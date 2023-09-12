package com.chocolate.presentation.screens.DMChat

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chocolate.presentation.Screen
import com.chocolate.viewmodel.DMChat.DMChatArgs
import com.chocolate.viewmodel.channel.ChannelArgs
import com.chocolate.viewmodel.topic.TopicArgs

fun NavGraphBuilder.dmChatRoute(){
    composable(
        route = "${Screen.DMChat.route}/{${DMChatArgs.MEMBERID}/{${DMChatArgs.MEMBER_NAME}/{${DMChatArgs.MEMBER_IMAGE}}",
        arguments = listOf(
            navArgument(DMChatArgs.MEMBERID) { NavType.StringType },
            navArgument(DMChatArgs.MEMBER_NAME) { NavType.StringType },
            navArgument(DMChatArgs.MEMBER_IMAGE) { NavType.StringType },
        )
    ){
        DMChatScreen()
    }
}

fun NavController.navigateToDmChat(
    memberId: String,
    memberName: String,
    memberImage: String,
){
    navigate("${Screen.DMChat.route}/$memberId/$memberName/$memberImage")
}