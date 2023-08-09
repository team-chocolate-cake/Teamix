package com.chocolate.presentation.screens.profile.component


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
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val color = MaterialTheme.customColors()
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onClick() },
        title = { Text(text = title) },
        text = { Text(text = text) },
        confirmButton = {
            TextButton(onClick = { }) {
                Text(text = stringResource(R.string.confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = { onClick() }) {
                Text(text = stringResource(R.string.dismiss))

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
