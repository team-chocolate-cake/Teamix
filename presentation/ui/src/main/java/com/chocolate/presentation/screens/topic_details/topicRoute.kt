package com.chocolate.presentation.screens.topic_details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chocolate.presentation.Screen
import com.chocolate.viewmodel.topic.TopicArgs

fun NavGraphBuilder.topicRoute(){
    composable(
        route = "${Screen.Topic.route}/{${TopicArgs.CHANNEL_ID}}/{${TopicArgs.TOPIC_ID}}/{${TopicArgs.TOPIC_NAME}}",
        arguments = listOf(
            navArgument(TopicArgs.CHANNEL_ID) { NavType.StringType },
            navArgument(TopicArgs.TOPIC_ID){NavType.StringType},
            navArgument(TopicArgs.TOPIC_NAME) { NavType.StringType }
        )
    ){
        TopicScreen()
    }
}

fun NavController.navigateToTopic(channelId:Int,topicId:String,topicName: String){
    navigate("${Screen.Topic.route}/$channelId/$topicId/$topicName")
}
