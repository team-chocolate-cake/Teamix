package com.chocolate.presentation.composable


import androidx.compose.foundation.layout.wrapContentSize
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
    onDismissButtonClick: () -> Unit,
    onConfirmButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val color = MaterialTheme.customColors()
    val typography = MaterialTheme.typography
    AlertDialog(
        modifier = modifier.wrapContentSize(),
        onDismissRequest = { onDismissButtonClick() },
        title = { Text(text = title, style = typography.titleMedium, color = color.onBackground87) },
        text = { Text(text = text, style = typography.bodySmall, color = color.onBackground60) },
        confirmButton = {
            TextButton(onClick = { onConfirmButtonClick() }) {
                Text(text = stringResource(R.string.confirm), style = typography.bodySmall, color = color.onBackground87)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissButtonClick() }) {
                Text(text = stringResource(R.string.dismiss), style = typography.bodySmall, color = color.onBackground87)

            }
        },
        containerColor = color.background
    )

}

@Preview(showBackground = true)
@Composable
fun ProfileDialogPreview() {
    ProfileDialog("title", "content", {}, {})
}
