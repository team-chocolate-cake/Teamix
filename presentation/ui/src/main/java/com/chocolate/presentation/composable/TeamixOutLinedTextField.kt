package com.chocolate.presentation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.Space12
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.customColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamixOutLinedTextField(
    text: String,
    onValueChange: (String) -> Unit,
    onDone: (() -> Unit)? = null,
    colorFocused: Color,
    colorUnFocused: Color,
    colorIcon: Color
) {
    val colors = MaterialTheme.customColors()
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Space16),
        value = text,
        onValueChange = { onValueChange(it) },
        shape = RoundedCornerShape(Space12),
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
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.pen),
                contentDescription = null,
                tint = colorIcon,
            )
        },
        singleLine = true,
        keyboardActions = KeyboardActions(onDone = {
            if (onDone != null) {
                onDone()
            }
        })
    )
}