package com.chocolate.presentation.screens.saveLater

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.saveLaterRoute(){
    composable(Screen.SaveLater.route){
        SaveLaterScreen()
    }
}

fun NavController.navigateToSaveLater(){
    navigate(Screen.SaveLater.route)
}