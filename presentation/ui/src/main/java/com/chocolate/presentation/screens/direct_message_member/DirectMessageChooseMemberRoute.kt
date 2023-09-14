package com.chocolate.presentation.screens.direct_message_member

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.directMessageChooseMemberRoute(){
    composable(
        route = Screen.DirectMessageChooseMember.route,
    ){
        DirectMessageChooseMemberScreen()
    }
}

fun NavController.navigateToDMChooseMember(){
    navigate(Screen.DirectMessageChooseMember.route)
}