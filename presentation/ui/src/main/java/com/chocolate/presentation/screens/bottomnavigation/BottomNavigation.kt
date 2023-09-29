package com.chocolate.presentation.screens.bottomnavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.chocolate.presentation.theme.SpacingMedium
import com.chocolate.presentation.theme.customColors

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Search,
        BottomNavigationItem.DMs,
        BottomNavigationItem.Profile
    )
    val color = MaterialTheme.customColors()
    Box( modifier = Modifier
        .background(color.card)
        .padding(horizontal = SpacingMedium)) {
        NavigationBar(
            containerColor = color.card,
        ) {
            items.forEach { item ->
                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = color.onPrimary,
                        selectedTextColor = color.primary,
                        indicatorColor = color.primary,
                        unselectedIconColor = color.onBackground60,
                        unselectedTextColor = color.onBackground60,
                    ),
                    selected = currentRoute(navController) == item.screenRoute,
                    alwaysShowLabel = false,
                    label = {
                        Text(
                            text = stringResource(id = item.title),
                            style = MaterialTheme.typography.labelMedium,
                        )
                    },
                    onClick = {
                        navController.navigate(item.screenRoute) {
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
                            contentDescription = stringResource(id = item.title),
                        )
                    }
                )
            }
        }
    }
}