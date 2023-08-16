package com.chocolate.presentation.screens.main_screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chocolate.presentation.screens.home.HomeScreen
import com.chocolate.presentation.screens.profile.ProfileScreen

@Composable
fun BottomNavigationNavGraph(navController: NavHostController) {

    NavHost(navController =navController , startDestination = BottomNavigationItem.Home.screen_route ){
        composable(BottomNavigationItem.Home.screen_route) {
            HomeScreen(navController = rememberNavController())
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
            ProfileScreen(navController = rememberNavController())
        }
    }
}