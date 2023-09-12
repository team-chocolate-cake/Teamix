package com.chocolate.presentation.screens.DMChat

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chocolate.presentation.Screen
import com.chocolate.viewmodel.DMChat.DMChatArgs

fun NavGraphBuilder.dmChatRoute(){
    composable(
        route = "${Screen.DMChat.route}/{${DMChatArgs.MEMBER_ID}}/{${DMChatArgs.MEMBER_NAME}}",
        arguments = listOf(
            navArgument(DMChatArgs.MEMBER_ID) { NavType.StringType },
            navArgument(DMChatArgs.MEMBER_NAME) { NavType.StringType },
        )
    ){
        DMChatScreen()
    }
}

fun NavController.navigateToDmChat(
    memberId: String,
    memberName: String,
){
    navigate("${Screen.DMChat.route}/$memberId/$memberName")
}