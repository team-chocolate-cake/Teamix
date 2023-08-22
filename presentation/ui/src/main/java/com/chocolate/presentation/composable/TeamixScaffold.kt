package com.chocolate.presentation.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chocolate.presentation.theme.customColors

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TeamixScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
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
        snackbarHost = {snackBarHost()},
        floatingActionButton = {floatingActionButton()},
        bottomBar = {bottomBar()}
    ) {paddingValues->
        content(paddingValues)
    }

}
