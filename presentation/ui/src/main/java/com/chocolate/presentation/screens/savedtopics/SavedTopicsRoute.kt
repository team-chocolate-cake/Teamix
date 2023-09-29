package com.chocolate.presentation.screens.savedtopics

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.savedTopicsRoute(){
    composable(Screen.SavedTopics.route){
        SavedTopicsScreen()
    }
}

fun NavController.navigateToSavedTopics(){
    navigate(Screen.SavedTopics.route)
}