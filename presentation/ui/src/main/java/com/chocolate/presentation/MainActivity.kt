package com.chocolate.teamix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.chocolate.teamix.ui.theme.TeamixTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            TeamixTheme {
                MaterialTheme.customColors().background

            }
        }
    }
}