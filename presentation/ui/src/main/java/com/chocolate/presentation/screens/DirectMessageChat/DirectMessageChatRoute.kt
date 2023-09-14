package com.chocolate.presentation.screens.DirectMessageChat

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chocolate.presentation.Screen
import com.chocolate.viewmodel.DMChat.DMChatArgs

fun NavGraphBuilder.dmChatRoute(){
    composable(
        route = "${Screen.DMChat.route}/{${DMChatArgs.GROUP_ID}}/{${DMChatArgs.MEMBER_NAME}}",
        arguments = listOf(
            navArgument(DMChatArgs.GROUP_ID) { NavType.StringType },
            navArgument(DMChatArgs.MEMBER_NAME) { NavType.StringType },
        )
    ){
        DirectMessageChatScreen()
    }
}

fun NavController.navigateToDmChat(
    groupId: String,
    memberName: String,
    popBackStack:Boolean = false
){
    if (popBackStack){popBackStack()}
    navigate("${Screen.DMChat.route}/$groupId/$memberName")
}