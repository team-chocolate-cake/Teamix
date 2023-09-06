package com.chocolate.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.screens.bottomNavigation.BottomNavigation
import com.chocolate.presentation.screens.bottomNavigation.BottomNavigationItem
import com.chocolate.presentation.screens.bottomNavigation.currentRoute
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.LocalNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TeamixApp(isDark: Boolean, isLoggedIn: Boolean) {
    val navController = rememberNavController()
    val shouldShowBottomNavigation = when (currentRoute(navController)) {
        BottomNavigationItem.Home.screenRoute,
        BottomNavigationItem.Profile.screenRoute,
        BottomNavigationItem.DMs.screenRoute,
        BottomNavigationItem.TasksOrganization.screenRoute,
        BottomNavigationItem.Search.screenRoute,
        -> true

        else -> false
    }
    TeamixScaffold(isDarkMode = isSystemInDarkTheme(), bottomBar = {
        if (shouldShowBottomNavigation && isLoggedIn)
            BottomNavigation(navController = navController)
    }) { innerPadding ->
        val systemUiController = rememberSystemUiController()
        systemUiController.setStatusBarColor(
            MaterialTheme.customColors().background, darkIcons = !isDark
        )
        systemUiController.setNavigationBarColor(Color.Black)
        Box(modifier = Modifier.padding(innerPadding)) {
            CompositionLocalProvider(LocalNavController provides navController) {
                TeamixNavGraph(navController = navController)
            }
        }
    }
}
