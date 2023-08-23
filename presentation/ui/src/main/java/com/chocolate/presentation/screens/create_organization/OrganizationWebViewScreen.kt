package com.chocolate.presentation.screens.create_organization

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chocolate.presentation.composable.TeamixAppBar
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.theme.customColors
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun CreateOrganizationWebViewScreen() {
    OrganizationWebViewContent()
}

const val ZULIP: String = "https://zulip.com/new/"
const val Title: String = "Teamix"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SetJavaScriptEnabled")
@Composable
fun OrganizationWebViewContent() {
    val state = rememberWebViewState(url = ZULIP)
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
