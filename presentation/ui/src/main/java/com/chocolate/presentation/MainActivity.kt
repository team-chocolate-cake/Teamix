package com.chocolate.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.chocolate.presentation.theme.TeamixTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.chocolate.presentation.screens.login.LoginScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            TeamixTheme {
                SetUpNavGraph()
            }
        }
    }
}