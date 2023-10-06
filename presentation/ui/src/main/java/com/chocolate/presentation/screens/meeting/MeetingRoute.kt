package com.chocolate.presentation.screens.meeting

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.chocolate.presentation.Screen

fun NavGraphBuilder.meetingRoute() {
    composable(
        route = Screen.Meeting.route
    ) {
        MeetingScreen()
    }
}

fun NavController.navigateToMeetingScreen() {
    navigate(Screen.Meeting.route)
}