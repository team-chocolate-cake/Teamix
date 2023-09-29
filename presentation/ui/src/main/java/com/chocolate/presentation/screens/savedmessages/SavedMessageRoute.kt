package com.chocolate.presentation.screens.savedmessages

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.savedMessageRoute(){
    composable(Screen.SavedMessage.route){
        SaveLaterScreen()
    }
}

fun NavController.navigateToSavedMessage(){
    navigate(Screen.SavedMessage.route)
}