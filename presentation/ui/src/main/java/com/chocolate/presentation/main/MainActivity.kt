package com.chocolate.presentation.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.TeamixApp
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.util.InstallSavedAppLanguage
import com.chocolate.viewmodel.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()
        setContent {
            val mainViewModel: MainViewModel = hiltViewModel()
            InstallSavedAppLanguage(this)
            val state by mainViewModel.state.collectAsState()
            TeamixTheme(state.isDark) {
                TeamixApp(state.isDark.collectAsState(initial = true).value,state.isLoggedIn)
            }
        }
    }
}