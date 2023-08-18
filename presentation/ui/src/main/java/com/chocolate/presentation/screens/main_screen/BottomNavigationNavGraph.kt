package com.chocolate.presentation.screens.main_screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chocolate.presentation.screens.create_organization.createOrganizationWebViewRoute
import com.chocolate.presentation.screens.forget_password.forgetPasswordWebViewRoute
import com.chocolate.presentation.screens.home.HomeScreen
import com.chocolate.presentation.screens.home.homeRoute
import com.chocolate.presentation.screens.login.loginRoute
import com.chocolate.presentation.screens.on_boarding.onboardingRoute
import com.chocolate.presentation.screens.organiztion.organizationNameRoute
import com.chocolate.presentation.screens.profile.ProfileScreen
import com.chocolate.presentation.screens.profile.profileRoute
import com.chocolate.presentation.screens.welcome.welcomeRoute
import com.chocolate.viewmodel.main.MainViewModel

@Composable
fun BottomNavigationNavGraph(navController: NavHostController,mainViewModel: MainViewModel) {

    NavHost(navController =navController , startDestination = BottomNavigationItem.Home.screen_route ){
        welcomeRoute(navController)
        onboardingRoute(navController)
        homeRoute(navController,mainViewModel)
        organizationNameRoute(navController)
        createOrganizationWebViewRoute(navController)
        loginRoute(navController)
        forgetPasswordWebViewRoute(navController)
        profileRoute(navController,mainViewModel)
       // ownerPowerRoute(navController)

        composable(BottomNavigationItem.Home.screen_route) {
            HomeScreen(navController,mainViewModel)
        }
        composable(BottomNavigationItem.Search.screen_route) {
            //todo add Search screen
        }
        composable(BottomNavigationItem.Tasks.screen_route) {
            //todo add Tasks screen
        }
        composable(BottomNavigationItem.DMs.screen_route) {
            //todo add DMs screen
        }
        composable(BottomNavigationItem.Profile.screen_route) {
            ProfileScreen(navController,mainViewModel)
        }
    }
}