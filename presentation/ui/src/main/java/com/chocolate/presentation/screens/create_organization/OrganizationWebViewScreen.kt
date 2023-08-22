package com.chocolate.presentation.screens.create_organization

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import com.chocolate.presentation.composable.TeamixAppBar
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.screens.organiztion.navigateToOrganizationName
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.LocalNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun CreateOrganizationWebViewScreen() {
    val navController = LocalNavController.current
    OrganizationWebViewContent(navigateToOrganizationName = { navController.navigateToOrganizationName() })
}

const val ZULIP: String = "https://zulip.com/new/"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SetJavaScriptEnabled")
@Composable
fun OrganizationWebViewContent(navigateToOrganizationName: () -> Unit) {
    val state = rememberWebViewState(url = ZULIP)
    val systemUiController = rememberSystemUiController()
    val color = MaterialTheme.customColors()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = color.secondary,
            darkIcons = true
        )
    }
    TeamixScaffold(
        topBar = {
            TeamixAppBar(
                title = "Teamix",
                navigationBack = { navigateToOrganizationName() },
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
