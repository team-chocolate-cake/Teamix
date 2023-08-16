package com.chocolate.presentation.screens.profile.composable


import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.customColors

@Composable
fun ProfileDialog(
    title: String,
    text: String,
    onClickDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    onClickConfirm: (() -> Unit) = {}
) {
    val color = MaterialTheme.customColors()
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onClickDismiss() },
        title = { Text(text = title) },
        text = { Text(text = text) },
        confirmButton = {
            TextButton(onClick = { onClickConfirm() }) {
                Text(text = stringResource(R.string.confirm),color = color.onBackground87)
            }
        },
        dismissButton = {
            TextButton(onClick = { onClickDismiss() }) {
                Text(text = stringResource(R.string.dismiss),color = color.onBackground87)

            }
        },
        containerColor = color.background
    )

}

@Preview(showBackground = true)
@Composable
fun ProfileDialogPreview() {
    ProfileDialog("title", "content", {})
}
