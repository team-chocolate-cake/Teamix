package com.chocolate.presentation.screens.main_screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.chocolate.presentation.Screen
import com.chocolate.presentation.screens.create_organization.createOrganizationWebViewRoute
import com.chocolate.presentation.screens.direct_message.directMessageRoute
import com.chocolate.presentation.screens.forget_password.forgetPasswordWebViewRoute
import com.chocolate.presentation.screens.home.homeRoute
import com.chocolate.presentation.screens.login.loginRoute
import com.chocolate.presentation.screens.on_boarding.onboardingRoute
import com.chocolate.presentation.screens.organiztion.organizationNameRoute
import com.chocolate.presentation.screens.profile.profileRoute
import com.chocolate.presentation.screens.search.searchRoute
import com.chocolate.presentation.screens.task.taskRoute
import com.chocolate.presentation.screens.welcome.welcomeRoute
import com.chocolate.viewmodel.main.MainViewModel

@Composable
fun BottomNavigationNavGraph(navController: NavHostController,mainViewModel: MainViewModel) {

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        welcomeRoute()
        onboardingRoute()
        homeRoute(mainViewModel)
        organizationNameRoute()
        createOrganizationWebViewRoute()
        loginRoute()
        forgetPasswordWebViewRoute()
        profileRoute(mainViewModel)
        searchRoute()
        directMessageRoute()
        taskRoute()
    }
}