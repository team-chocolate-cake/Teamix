package com.chocolate.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.chocolate.presentation.screens.main_screen.BottomNavigationNavGraph
import com.chocolate.presentation.screens.main_screen.composables.MainScreenBottomNavigation
import com.chocolate.presentation.theme.TeamixTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            TeamixTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { MainScreenBottomNavigation(navController =navController ) }
                ) { padding->
                    SetUpNavGraph()
                    BottomNavigationNavGraph(navController = navController)
                }
            }
        }
    }
}
