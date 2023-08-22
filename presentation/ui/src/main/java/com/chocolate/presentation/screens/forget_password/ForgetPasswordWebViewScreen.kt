package com.chocolate.presentation.screens.forget_password

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import com.chocolate.presentation.composable.TeamixAppBar
import com.chocolate.presentation.screens.create_organization.ZULIP
import com.chocolate.presentation.screens.login.backToLogin
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.LocalNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun ForgetPasswordWebViewScreen() {
    val navController = LocalNavController.current
    ForgetPasswordWebViewContent(navigateToLogin = { navController.backToLogin() })
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SetJavaScriptEnabled")
@Composable
fun ForgetPasswordWebViewContent(navigateToLogin: () -> Unit) {
    val state = rememberWebViewState(url = ZULIP)
    val systemUiController = rememberSystemUiController()
    val color = MaterialTheme.customColors()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = color.secondary,
            darkIcons = true
        )
    }

    Scaffold(
        topBar = {
            TeamixAppBar(
                title = "Teamix",
                navigationBack = { navigateToLogin() },
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
