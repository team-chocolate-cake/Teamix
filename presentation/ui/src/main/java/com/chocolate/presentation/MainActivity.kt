package com.chocolate.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.chocolate.presentation.screens.topic_details.TopicScreen
import com.chocolate.presentation.theme.TeamixTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeamixTheme {
                TopicScreen()

            }
        }
    }
}