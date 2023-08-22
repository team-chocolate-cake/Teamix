package com.chocolate.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.screens.bottomNavigation.BottomNavigation
import com.chocolate.presentation.screens.bottomNavigation.BottomNavigationItem
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.main.MainViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TeamixApp(isDark: Boolean, mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val shouldShowBottomNavigation = when (navBackStackEntry?.destination?.route) {
        BottomNavigationItem.Home.screen_route,
        BottomNavigationItem.Profile.screen_route,
        BottomNavigationItem.DMs.screen_route,
        BottomNavigationItem.Tasks.screen_route,
        BottomNavigationItem.Search.screen_route -> true
        else -> false
    }
    TeamixScaffold(bottomBar = {
        if (shouldShowBottomNavigation)
            BottomNavigation(navController = navController)
    }) { innerPadding ->
        val systemUiController = rememberSystemUiController()
        systemUiController.setStatusBarColor(
            MaterialTheme.customColors().background, darkIcons = !isDark
        )
        systemUiController.setNavigationBarColor(Color.Black)
        Box(modifier = Modifier.padding(innerPadding)) {
            TeamixNavGraph(navController = navController, mainViewModel)
        }
    }
}
