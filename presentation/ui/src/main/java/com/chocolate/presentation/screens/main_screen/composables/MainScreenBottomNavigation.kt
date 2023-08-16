package com.chocolate.presentation.screens.main_screen.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.chocolate.presentation.screens.main_screen.BottomNavigationItem
import com.chocolate.presentation.theme.customColors

@Composable
fun MainScreenBottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Search,
        BottomNavigationItem.Tasks,
        BottomNavigationItem.DMs,
        BottomNavigationItem.Profile
    )
    NavigationBar(
        containerColor = Color.White //todo must changed for the dark theme
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.customColors().onPrimary,
                    selectedTextColor = MaterialTheme.customColors().primary,
                    indicatorColor = MaterialTheme.customColors().primary,
                    unselectedIconColor = MaterialTheme.customColors().onBackground60,
                    unselectedTextColor = MaterialTheme.customColors().onBackground60,
                ),
                selected = currentRoute == item.screen_route,
                alwaysShowLabel = false,
                label = {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.labelMedium
                    )
                },
                onClick = {
                    navController.navigate(item.screen_route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                }
            )
        }
    }
}