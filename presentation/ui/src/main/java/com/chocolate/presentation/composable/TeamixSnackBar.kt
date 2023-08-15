package com.chocolate.presentation.composable

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun TeamixSnackBar(
    text: String,
    onClickButton: () -> Unit,
    modifier: Modifier = Modifier,
    action: String = ""
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        content = { innerPadding ->
            scope.launch {
                val snackbar = snackbarHostState.showSnackbar(
                    message = text,
                    actionLabel = action,
                    duration = SnackbarDuration.Short
                    )
                when(snackbar){
                    SnackbarResult.Dismissed -> {}
                    SnackbarResult.ActionPerformed -> onClickButton()
                }

            }
        }
    )

}