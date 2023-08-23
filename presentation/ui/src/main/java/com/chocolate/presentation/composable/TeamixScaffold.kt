package com.chocolate.presentation.composable

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.base.BaseErrorUiState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TeamixScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    isLoading: Boolean = false,
    onLoading: @Composable () -> Unit = {},
    error: BaseErrorUiState? = null,
    onError: @Composable () -> Unit = {},
    onRetry: () -> Unit = {},
    isDarkMode: Boolean,
    snackBarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    val colors = MaterialTheme.customColors()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { topBar() },
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
    error: BaseErrorUiState?,
    onError: @Composable () -> Unit,
    onRetry: () -> Unit,
    isDarkMode: Boolean
) {
    if (error?.message?.isNotEmpty() == true) {
        NoInternetLottie(
            onClickRetry = { onRetry() },
            isShow = error.isError,
            isDarkMode = isDarkMode
        )
    } else {
        onError()
    }
}