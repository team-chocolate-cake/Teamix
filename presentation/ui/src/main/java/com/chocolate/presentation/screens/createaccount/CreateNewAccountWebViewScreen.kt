package com.chocolate.presentation.screens.createaccount

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.theme.OnLightPrimary
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.createaccount.CreateNewAccountUiState
import com.chocolate.viewmodel.createaccount.CreateNewAccountViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun CreateNewAccountWebViewScreen(
    viewModel: CreateNewAccountViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    CreateNewAccountWebViewContent(state)
}

const val Title: String = "Teamix"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SetJavaScriptEnabled")
@Composable
fun CreateNewAccountWebViewContent(state: CreateNewAccountUiState) {
    val state = rememberWebViewState(
        url =
        "https://${state.organizationsName}.zulipchat.com/register/"
    )
    val systemUiController = rememberSystemUiController()
    val color = MaterialTheme.customColors()
    systemUiController.setSystemBarsColor(color = color.secondary, darkIcons = true)

    TeamixScaffold(
        title = Title,
        hasAppBar = true,
        titleColor = OnLightPrimary,
        hasBackArrow = true,
        isDarkMode = isSystemInDarkTheme()
    ) {
        WebView(
            state = state,
            modifier = Modifier.fillMaxSize(),
            onCreated = {
                it.settings.javaScriptEnabled = true
            }
        )
    }
}
