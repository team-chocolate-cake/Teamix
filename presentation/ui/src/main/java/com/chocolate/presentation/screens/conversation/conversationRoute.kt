package com.chocolate.presentation.screens.conversation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chocolate.presentation.Screen
import com.chocolate.viewmodel.conversation.ConversationArgs

fun NavGraphBuilder.conversationRoute(){
    composable(
        route = "${Screen.Conversation.route}/{${ConversationArgs.FRIEND_ID}}",
        arguments = listOf(navArgument(ConversationArgs.FRIEND_ID) { NavType.IntType },)
    ){
        ConversationScreen()
    }
}

fun NavController.navigateToConversation(friendId: Int){
    navigate("${Screen.Conversation.route}/$friendId")
}