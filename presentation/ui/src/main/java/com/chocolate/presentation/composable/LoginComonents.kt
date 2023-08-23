package com.chocolate.presentation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.customColors

@Composable
fun LoginComponents(
    email: String,
    password: String,
    passwordVisibility: Boolean,
    onClickPasswordVisibility:(Boolean) -> Unit,
    onChangeEmail: (String) -> Unit,
    onChangePassword: (String) -> Unit
) {
    val colors = MaterialTheme.customColors()
    Text(
        modifier = Modifier,
        text = stringResource(R.string.email),
        style = MaterialTheme.typography.labelMedium,
        color = colors.onBackground87
    )
    TeamixTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Space8),
        value = email,
        onValueChange = { onChangeEmail(it) },
        hint = stringResource(id = R.string.email))

    Text(
        modifier = Modifier.padding(top = Space16),
        text = stringResource(R.string.password),
        style = MaterialTheme.typography.labelMedium,
        color = colors.onBackground87
    )
    val passwordIcon =
        if (passwordVisibility) R.drawable.ic_eye else R.drawable.ic_eye_closed
    TeamixTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Space8),
        value = password,
        onValueChange = { onChangePassword(it) },
        trailingIcon = {
            IconButton(onClick = { onClickPasswordVisibility(!passwordVisibility) }) {
                Icon(painter = painterResource(id = passwordIcon), contentDescription = null)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        hint = stringResource(id = R.string.password)
    )
}