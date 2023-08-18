package com.chocolate.presentation.screens.direct_message

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun DirectMessageScreen(navController: NavController) {
    DirectMessageContent()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DirectMessageContent() {
    val context = LocalContext.current
    Scaffold {
        Toast.makeText(context, "Direct Message Screen", Toast.LENGTH_SHORT).show()
    }
}
