package com.chocolate.presentation.screens.topic_details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chocolate.presentation.Screen
import com.chocolate.viewmodel.channel.ChannelArgs
import com.chocolate.viewmodel.topic.TopicArgs

fun NavGraphBuilder.topicRoute(){
    composable(
        route = "${Screen.Topic.route}/{${TopicArgs.TOPIC_NAME}}",
        arguments = listOf(navArgument(TopicArgs.TOPIC_NAME) { NavType.StringType },)
    ){
        TopicScreen()
    }
}

fun NavController.navigateToTopic(topicName: String){
    navigate("${Screen.Topic.route}/$topicName")
}