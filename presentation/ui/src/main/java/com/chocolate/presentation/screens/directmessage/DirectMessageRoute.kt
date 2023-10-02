package com.chocolate.presentation.screens.directmessage

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.directMessageRoute() {
    composable(Screen.DirectMessage.route) {
        DirectMessageScreen()
    }
}