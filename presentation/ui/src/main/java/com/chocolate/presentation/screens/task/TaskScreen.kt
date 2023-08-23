package com.chocolate.presentation.screens.task

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.util.LocalNavController

@Composable
fun TaskScreen() {
    val navController = LocalNavController.current
    TaskContent()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskContent() {
    val context = LocalContext.current
    TeamixScaffold(isDarkMode = isSystemInDarkTheme()) {
        Toast.makeText(context, "Task Screen", Toast.LENGTH_SHORT).show()
    }
}
