package com.chocolate.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.util.installSavedAppLanguage
import com.chocolate.viewmodel.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            installSavedAppLanguage(this)
            val mainViewModel: MainViewModel = hiltViewModel()
            val isDarkTheme by mainViewModel.state.collectAsState()
            TeamixTheme(isDarkTheme || isSystemInDarkTheme()) {
                TeamixApp(isDarkTheme || isSystemInDarkTheme(), mainViewModel)
            }
        }
    }
}


