package com.chocolate.presentation.screens.task

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun TaskScreen(navController: NavController) {
    TaskContent()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskContent() {
    val context = LocalContext.current
    Scaffold {
        Toast.makeText(context, "Task Screen", Toast.LENGTH_SHORT).show()
    }
}
