package com.chocolate.presentation.screens.search

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun SearchScreen(navController: NavController) {
    SearchContent()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchContent() {
    val context = LocalContext.current
    Scaffold {
        Toast.makeText(context, "Search Screen", Toast.LENGTH_SHORT).show()
    }
}
