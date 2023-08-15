package com.chocolate.presentation.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TeamixSnackBar(
    text: String,
    onClickButton: () -> Unit,
    modifier: Modifier = Modifier,
    action: String = ""
) {
    var (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(true) }
    if (snackbarVisibleState) {
        Snackbar(
            modifier = modifier,
            action = {
                Text(text = action, modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable { onClickButton() })
            },
            dismissAction = {
                Text(
                    text = "Ok",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable {!snackbarVisibleState })
            }) {
            Text(text = text)
        }
    }

}