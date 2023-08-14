package com.chocolate.presentation.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TeamixSnackBar(
    text: String,
    onClickButton: () -> Unit,
    modifier: Modifier = Modifier,
    state: String = ""
) {

    Snackbar(
        modifier = modifier,
        action = {
            Text(text = state, modifier = Modifier.clickable { onClickButton() })
        },
        dismissAction = {}) {
        Text(text = text)
    }
}