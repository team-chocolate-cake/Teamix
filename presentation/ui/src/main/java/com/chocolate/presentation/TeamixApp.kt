package com.chocolate.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.screens.bottomnavigation.BottomNavigation
import com.chocolate.presentation.screens.bottomnavigation.BottomNavigationItem
import com.chocolate.presentation.screens.bottomnavigation.currentRoute
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.LocalNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("SuspiciousIndentation")
@Composable
fun TeamixApp(isDarkMode:Boolean) {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val shouldShowBottomNavigation = when (currentRoute(navController)) {
        BottomNavigationItem.Home.screenRoute,
        BottomNavigationItem.Profile.screenRoute,
        BottomNavigationItem.DMs.screenRoute,
        BottomNavigationItem.Search.screenRoute,
        -> true
        else -> false
    }
    val shouldChangeStatusBarColor =when (currentRoute(navController)) {
        BottomNavigationItem.Home.screenRoute,
        BottomNavigationItem.Search.screenRoute,-> false
        else -> true
    }

    TeamixScaffold(
        bottomBar = { if (shouldShowBottomNavigation) BottomNavigation(navController = navController) }
    ) { innerPadding ->
        if(shouldChangeStatusBarColor)
        systemUiController.setStatusBarColor(MaterialTheme.customColors().background, darkIcons = !isDarkMode)
        systemUiController.setNavigationBarColor(Color.Black)
        Box(modifier = Modifier.padding(innerPadding)) {
            CompositionLocalProvider(LocalNavController provides navController) {
                TeamixNavGraph(navController = navController)
            }
        }
    }
}
