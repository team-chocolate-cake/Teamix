package com.chocolate.presentation.screens.topicmessages

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chocolate.presentation.Screen
import com.chocolate.viewmodel.topicmessages.TopicMessagesArgs

fun NavGraphBuilder.topicRoute(){
    composable(
        route = "${Screen.Topic.route}/{${TopicMessagesArgs.CHANNEL_ID}}/{${TopicMessagesArgs.TOPIC_ID}}/{${TopicMessagesArgs.TOPIC_NAME}}",
        arguments = listOf(
            navArgument(TopicMessagesArgs.CHANNEL_ID) { NavType.StringType },
            navArgument(TopicMessagesArgs.TOPIC_ID){NavType.StringType},
            navArgument(TopicMessagesArgs.TOPIC_NAME) { NavType.StringType }
        )
    ){
        TopicScreen()
    }
}

fun NavController.navigateToTopic(channelId:Int,topicId:String,topicName: String){
    navigate("${Screen.Topic.route}/$channelId/$topicId/$topicName")
}
