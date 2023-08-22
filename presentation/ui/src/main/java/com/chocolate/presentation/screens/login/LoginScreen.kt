package com.chocolate.presentation.screens.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.Button
import com.chocolate.presentation.composable.TeamixTextField
import com.chocolate.presentation.screens.forget_password.navigateToForgetPassword
import com.chocolate.presentation.screens.home.navigateToHome
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space24
import com.chocolate.presentation.theme.Space4
import com.chocolate.presentation.theme.Space42
import com.chocolate.presentation.theme.Space48
import com.chocolate.presentation.theme.Space56
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.login.LoginInteraction
import com.chocolate.viewmodel.login.LoginUiEffect
import com.chocolate.viewmodel.login.LoginUiState
import com.chocolate.viewmodel.login.LoginViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val state by loginViewModel.state.collectAsState()
    val navController = LocalNavController.current
    LaunchedEffect(key1 = Unit ){
    val scrollState = rememberScrollState()
    LaunchedEffect(key1 = Unit) {
        loginViewModel.effect.collectLatest { effect ->
            when (effect) {
                LoginUiEffect.NavigateToForgetPassword -> navController.navigateToForgetPassword()
                LoginUiEffect.NavigationToHome -> navController.navigateToHome()
            }
        }
    }
    LoginContent(
        loginInteraction = loginViewModel,
        navigateToForgetPassword = loginViewModel::onClickForgetPassword,
        state = state,
        scrollState
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginContent(
    loginInteraction: LoginInteraction,
    navigateToForgetPassword: () -> Unit,
    state: LoginUiState,
    scrollState: ScrollState
) {
    val colors = MaterialTheme.customColors()
    val context = LocalContext.current
    val errorMessage = stringResource(R.string.email_and_password_cannot_be_empty)

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.background)
            .padding(horizontal = Space16)
            .verticalScroll(scrollState),
    ) {
        Text(
            modifier = Modifier.padding(top = Space42),
            text = stringResource(R.string.welcome_to),
            style = MaterialTheme.typography.titleLarge,
            color = colors.onBackground87
        )
        Text(
            modifier = Modifier.padding(bottom = Space48),
            text = state.nameOrganization,
            style = MaterialTheme.typography.titleLarge,
            color = colors.primary
        )

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
            value = state.email,
            onValueChange = { email ->
                loginInteraction.updateEmailState(email)
            },
            trailingIcon = {},
            placeholder = {})

        Text(
            modifier = Modifier.padding(top = Space16),
            text = stringResource(R.string.password),
            style = MaterialTheme.typography.labelMedium,
            color = colors.onBackground87
        )
        val passwordIcon =
            if (state.passwordVisibility) R.drawable.ic_eye else R.drawable.ic_eye_closed
        TeamixTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Space8),
            value = state.password,
            onValueChange = { password -> loginInteraction.updatePasswordState(password) },
            placeholder = {},
            trailingIcon = {
                IconButton(onClick = { loginInteraction.onClickpasswordVisibility(!state.passwordVisibility) }) {
                    Icon(painter = painterResource(id = passwordIcon), contentDescription = null)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (state.passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Space4, bottom = Space24, end = Space16),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                stringResource(R.string.forget_password),
                fontSize = 14.sp,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.clickable { navigateToForgetPassword() },
                color = colors.primary,
            )
        }
        Button(
            onClick = {
                if (state.email.isBlank() || state.password.isBlank()) {
                    Toast.makeText(
                        context,
                        errorMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                if (state.error != null) {
                    Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                }
                loginInteraction.login(state.email, state.password)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(Space56),
            colors = colors,
        ) {
            AnimatedVisibility(visible = state.isLoading) {
                CircularProgressIndicator(
                    color = colors.card,
                    modifier = Modifier
                        .size(Space24)
                        .align(Alignment.CenterVertically)
                )
            }
            AnimatedVisibility(visible = !state.isLoading) {
                Text(
                    text = stringResource(R.string.sign_in),
                    style = MaterialTheme.typography.bodyLarge,
                    color = colors.onPrimary
                )
            }
        }
    }
}