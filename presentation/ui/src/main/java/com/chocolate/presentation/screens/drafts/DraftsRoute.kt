package com.chocolate.presentation.screens.drafts

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.draftsRoute(){
    composable(Screen.Drafts.route){
        DraftsScreen()
    }
}

fun NavController.navigateToDrafts(){
    navigate(Screen.Drafts.route)
}