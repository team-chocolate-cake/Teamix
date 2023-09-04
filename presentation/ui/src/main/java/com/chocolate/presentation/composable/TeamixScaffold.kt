package com.chocolate.presentation.composable

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.customColors

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TeamixScaffold(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean,
    isLoading: Boolean = false,
    error: String? = null,
    title: String = "",
    titleColor: Color = MaterialTheme.customColors().onBackground87,
    imageUrl: String = "",
    hasAppBar: Boolean = false,
    hasImageUrl: Boolean = false,
    hasBackArrow: Boolean = false,
    containerColorAppBar: Color = MaterialTheme.customColors().card,
    onLoading: @Composable () -> Unit = {},
    onRetry: () -> Unit = {},
    onError: @Composable () -> Unit = {},
    actionsAppbar: @Composable() (RowScope.() -> Unit) = {},
    snackBarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    val colors = MaterialTheme.customColors()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            AnimatedVisibility(visible = hasAppBar) {
                TeamixAppBar(
                    title = title,
                    titleColor = titleColor,
                    hasBackArrow = hasBackArrow,
                    hasImageUrl = hasImageUrl,
                    imageUrl = imageUrl,
                    actions = {actionsAppbar()},
                    containerColor = containerColorAppBar
                )

            }
        },
        containerColor = colors.background,
        snackbarHost = { snackBarHost() },
        floatingActionButton = { floatingActionButton() },
        bottomBar = { bottomBar() }
    ) { paddingValues ->
        AnimatedVisibility(visible = isLoading) { onLoading() }
        AnimatedVisibility(visible = error != null) {
            ErrorHandler(
                error = error,
                onError = { onError() },
                onRetry = { onRetry() },
                isDarkMode = isDarkMode
            )
        }
        AnimatedVisibility(visible = !isLoading && error == null) {
            content(paddingValues)
        }

    }
}

@Composable
fun ErrorHandler(
    error: String?,
    onError: @Composable () -> Unit,
    onRetry: () -> Unit,
    isDarkMode: Boolean
) {
    if (error?.isNotEmpty() == true) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            NoInternetLottie(
                onClickRetry = { onRetry() },
                isShow = error.isNotEmpty(),
                isDarkMode = isDarkMode,
                text = stringResource(id = R.string.no_internet_connection),
                modifier = Modifier.fillMaxSize()
            )
        }

    } else {
        onError()
    }
}