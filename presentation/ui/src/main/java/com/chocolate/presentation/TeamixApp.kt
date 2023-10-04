package com.chocolate.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
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
fun TeamixApp(isDarkMode: Boolean, isLoggedIn: Boolean) {
    val colors = MaterialTheme.customColors()
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val shouldShowBottomNavigation = (currentRoute(navController)) in listOf(
        BottomNavigationItem.Home.screenRoute,
        BottomNavigationItem.Profile.screenRoute,
        BottomNavigationItem.DMs.screenRoute,
        BottomNavigationItem.Search.screenRoute,
    )
    val shouldUseLightStatusBarIcons = (currentRoute(navController)) in listOf(
        BottomNavigationItem.Home.screenRoute,
        BottomNavigationItem.DMs.screenRoute,
    )

    TeamixScaffold(
        bottomBar = { if (shouldShowBottomNavigation && isLoggedIn) BottomNavigation(navController = navController) },
    ) { innerPadding ->
        systemUiController.setStatusBarColor(
            color = colors.transparent,
            darkIcons = if (shouldUseLightStatusBarIcons) false else !isDarkMode
        )
        systemUiController.setNavigationBarColor(colors.black)
        Box(modifier = Modifier.padding(innerPadding)) {
            CompositionLocalProvider(LocalNavController provides navController) {
                TeamixNavGraph(navController = navController)
            }
        }
    }
}
