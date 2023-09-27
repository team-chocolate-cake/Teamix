package com.chocolate.presentation

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
import com.chocolate.presentation.util.LocalNavController

@Composable
fun TeamixApp() {
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
    TeamixScaffold(statusBarColor = MaterialTheme.colorScheme.background,
        bottomBar = { if (shouldShowBottomNavigation) BottomNavigation(navController = navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            CompositionLocalProvider(LocalNavController provides navController) {
                TeamixNavGraph(navController = navController)
            }
        }
    }
}
