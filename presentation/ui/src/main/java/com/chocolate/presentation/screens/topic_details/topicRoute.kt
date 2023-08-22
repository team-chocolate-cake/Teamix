package com.chocolate.presentation.screens.topic_details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.topicRoute(){
    composable(Screen.Topic.route){
        TopicScreen()
    }
}

fun NavController.navigateToTopic(){
    navigate(Screen.Topic.route)
}