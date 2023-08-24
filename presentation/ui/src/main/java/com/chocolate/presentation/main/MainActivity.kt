package com.chocolate.presentation.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.chocolate.presentation.TeamixApp
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.util.InstallSavedAppLanguage
import com.chocolate.viewmodel.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel:MainViewModel by viewModels()
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            InstallSavedAppLanguage(this)
            val isDarkTheme by mainViewModel.state.collectAsState()
            TeamixTheme(isDarkTheme || isSystemInDarkTheme()) {
                TeamixApp(isDarkTheme || isSystemInDarkTheme())
            }
        }
    }
}