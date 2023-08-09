package com.chocolate.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.chocolate.viewmodel.TestViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        val viewModel: TestViewModel by viewModels()


        setContent {
            TeamixTheme {
                viewModel.test()

            }
        }
    }
}