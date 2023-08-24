package com.chocolate.presentation.screens.chooseMember

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.chooseMemberRoute(){
    composable(Screen.ChooseMembers.route){
        ChooseMemberScreen()
    }
}

fun NavController.navigateToChooseMember(){
    navigate(Screen.ChooseMembers.route)
}