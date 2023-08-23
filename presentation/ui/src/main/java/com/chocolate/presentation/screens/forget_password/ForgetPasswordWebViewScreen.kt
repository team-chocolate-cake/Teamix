package com.chocolate.presentation.screens.forget_password

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.composable.TeamixAppBar
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.forgetPassowrd.ForgetPasswordUiState
import com.chocolate.viewmodel.forgetPassowrd.ForgetPasswordViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun ForgetPasswordWebViewScreen(
    viewModel: ForgetPasswordViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    ForgetPasswordWebViewContent(state)
}
const val Title: String = "Teamix"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SetJavaScriptEnabled")
@Composable
fun ForgetPasswordWebViewContent(forgetPasswordUiState: ForgetPasswordUiState) {
    val state = rememberWebViewState(url = "https://${forgetPasswordUiState.organizationsName}.zulipchat.com/accounts/password/reset/")
    val systemUiController = rememberSystemUiController()
    val color = MaterialTheme.customColors()
    systemUiController.setSystemBarsColor(color = color.secondary, darkIcons = true)

    TeamixScaffold(
        topBar = {
            TeamixAppBar(
                title = Title,
                containerColor = color.secondary
            ) {}
        }
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
