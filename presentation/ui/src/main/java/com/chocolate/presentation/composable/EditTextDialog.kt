package com.chocolate.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.SpacingHuge
import com.chocolate.presentation.theme.SpacingMedium
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXXSmall
import com.chocolate.presentation.theme.customColors

@Composable
fun EditTextDialog(
    title: String,
    value: String,
    dismissButton: () -> Unit,
    confirmButton: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    var text by rememberSaveable { mutableStateOf(value) }
    var error by rememberSaveable { mutableStateOf("") }
    val errorMessage = stringResource(id = R.string.full_name_can_t_be_empty)
    val color = MaterialTheme.customColors()

    Dialog(onDismiss) {
        Surface(shape = MaterialTheme.shapes.medium) {
            Column {
                Column(Modifier.padding(SpacingHuge)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Spacer(Modifier.size(SpacingXLarge))
                    TeamixOutLinedTextField(
                        text = text,
                        onValueChange = {
                            text = it
                            error = ""
                        },
                        colorFocused = color.primary,
                        colorUnFocused = color.background,
                        error = error
                    )
                }
                Spacer(Modifier.size(SpacingXXSmall))
                Row(
                    Modifier
                        .padding(SpacingMedium)
                        .fillMaxWidth(),
                    Arrangement.spacedBy(SpacingMedium, Alignment.End),
                ) {
                    TextButton(
                        onClick = dismissButton,
                        content = {
                            Text(
                                stringResource(id = R.string.cancel),
                                color = color.onBackground87
                            )
                        },
                    )
                    TextButton(
                        onClick = {
                            if (text.isNotBlank()) {
                                confirmButton(text)
                                dismissButton()
                            } else {
                                error = errorMessage
                            }
                        },
                        content = { Text(stringResource(R.string.ok), color = color.primary) },
                    )
                }
            }
        }
    }
}