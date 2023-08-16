package com.chocolate.presentation.screens.main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavigationNavGraph(navController: NavHostController) {

    NavHost(navController =navController , startDestination = BottomNavigationItem.Home.screen_route ){
        composable(BottomNavigationItem.Home.screen_route) {
            //todo add Home screen
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
            //todo add Profile screen
        }
    }
}