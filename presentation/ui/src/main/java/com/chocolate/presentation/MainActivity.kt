package com.chocolate.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.chocolate.presentation.screens.main_screen.BottomNavigationItem
import com.chocolate.presentation.screens.main_screen.BottomNavigationNavGraph
import com.chocolate.presentation.screens.main_screen.composables.MainScreenBottomNavigation
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.presentation.util.installSavedAppLanguage
import com.chocolate.viewmodel.main.MainViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            installSavedAppLanguage(this)
            val userSettingsViewModel: MainViewModel = hiltViewModel()
            val isDarkTheme by userSettingsViewModel.state.collectAsState()
            TeamixTheme(isDarkTheme) {
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
                Scaffold (  bottomBar = {
                    if (shouldShowBottomNavigation)
                        MainScreenBottomNavigation(navController = navController)
                }){innerPadding ->
                    val isSystemInDarkMode = isSystemInDarkTheme()
                    val systemUiController = rememberSystemUiController()
                    systemUiController.setStatusBarColor(
                        MaterialTheme.customColors().background, darkIcons = !isSystemInDarkMode
                    )
                    ApplySystemUi(isDarkTheme)
                    Box(modifier = Modifier.padding(innerPadding)) {
                        CompositionLocalProvider(LocalNavController provides navController) {
                            BottomNavigationNavGraph(
                                navController = navController,
                                userSettingsViewModel
                            )
                        }
                    }
                    }
                }
            }
        }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ApplySystemUi(isDark: Boolean) {
    Scaffold {
        val systemUiController = rememberSystemUiController()
        systemUiController.setStatusBarColor(
            MaterialTheme.customColors().background, darkIcons = !isDark
        )
        systemUiController.setNavigationBarColor(Color.Black)
    }
}



