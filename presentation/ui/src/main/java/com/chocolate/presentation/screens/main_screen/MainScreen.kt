package com.chocolate.presentation.screens.main_screen

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.chocolate.presentation.screens.main_screen.composables.MainScreenBottomNavigation
import com.chocolate.presentation.theme.TeamixTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { MainScreenBottomNavigation(navController =navController ) }
    ) { padding->
        // BottomNavigationNavGraph(navController = navController)
    }
}

@Composable
@Preview(showSystemUi = true)
fun MainScreenPreview() {
    TeamixTheme() {
        MainScreen()
    }
}