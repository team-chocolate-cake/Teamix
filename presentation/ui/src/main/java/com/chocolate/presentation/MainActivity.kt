package com.chocolate.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeamixTheme {
                MaterialTheme.customColors().background

            }
        }
    }
}