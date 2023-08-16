package com.chocolate.presentation.screens.forget_password

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chocolate.viewmodel.home.HomeUiState
import com.chocolate.viewmodel.login.LoginUiState
import com.chocolate.viewmodel.login.LoginViewModel
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun ForgetPasswordWebViewScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val state by loginViewModel.state.collectAsState()
    ForgetPasswordWebViewContent(state)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SetJavaScriptEnabled")
@Composable
fun ForgetPasswordWebViewContent(loginUiState: LoginUiState) {
    val state = rememberWebViewState(url = "https://${loginUiState.nameOrganization}.zulipchat.com/accounts/password/reset//")
    Scaffold {
        WebView(
            state = state,
            modifier = Modifier.fillMaxSize(),
            onCreated = {
                it.settings.javaScriptEnabled = true
            }
        )
    }
}
