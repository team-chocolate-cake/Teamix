package com.chocolate.presentation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXXMedium
import com.chocolate.presentation.theme.customColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamixOutLinedTextField(
    text: String,
    onValueChange: (String) -> Unit,
    error: String = "",
    onDone: (() -> Unit)? = null,
    colorFocused: Color,
    colorUnFocused: Color,
    readOnly: Boolean = false,
    trailingIcon: @Composable () -> Unit = {}
) {
    val colors = MaterialTheme.customColors()
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = SpacingXLarge),
        value = text,
        onValueChange = { onValueChange(it) },
        shape = RoundedCornerShape(SpacingXXMedium),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = colors.card,
            focusedBorderColor = colorFocused,
            unfocusedBorderColor = colorUnFocused,
            cursorColor = colors.onBackground87,
            selectionColors = TextSelectionColors(
                handleColor = colors.primary,
                backgroundColor = colors.primary
            )
        ),
        trailingIcon = trailingIcon,
        singleLine = true,
        keyboardActions = KeyboardActions(onDone = {
            if (onDone != null) {
                onDone()
            }
        }),
        readOnly = readOnly,
        isError = error.isNotEmpty(),
        supportingText = {
            Text(text = error)
        }

    )
}