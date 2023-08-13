package com.chocolate.presentation.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.chocolate.presentation.screens.forget_password.navigateToForgetPassword
import com.chocolate.presentation.screens.home.navigateToHome
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.login.LoginInteraction
import com.chocolate.viewmodel.login.LoginUiEffect
import com.chocolate.viewmodel.login.LoginUiState
import com.chocolate.viewmodel.login.LoginViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val state by loginViewModel.state.collectAsState()
    LaunchedEffect(key1 = Unit ){
        loginViewModel.effect.collectLatest { effect ->
            when(effect){
                LoginUiEffect.NavigateToForgetPassword -> navController.navigateToForgetPassword()
                LoginUiEffect.NavigationToHome -> navController.navigateToHome()
            }
        }
    }
    LoginContent(
        loginInteraction = loginViewModel,
        navigateToForgetPassword = { navController.navigateToForgetPassword() },
        state = state
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(
    loginInteraction: LoginInteraction,
    navigateToForgetPassword: () -> Unit,
    state: LoginUiState
) {
    val colors = MaterialTheme.customColors()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.background)
            .padding(horizontal = 16.dp),
    ) {
        Text(
            modifier = Modifier.padding(top = 42.dp),
            text = stringResource(R.string.welcome_to),
            style = MaterialTheme.typography.titleLarge,
            color = colors.onBackground87
        )
        Text(
            modifier = Modifier.padding(bottom = 48.dp),
            text = state.nameOrganization,
            style = MaterialTheme.typography.titleLarge,
            color = colors.primary
        )

        Text(
            modifier = Modifier,
            text = stringResource(R.string.email),
            style = MaterialTheme.typography.labelMedium
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = state.email,
            onValueChange = { email ->
                loginInteraction.updateEmailState(email)
            },
            placeholder = { Text("", color = Color.Black.copy(alpha = 0.6f)) },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = colors.card,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = Color.Black
            )
        )

        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.password),
            style = MaterialTheme.typography.labelMedium
        )
        var passwordVisibility: Boolean by remember { mutableStateOf(false) }
        val passwordIcon = if (passwordVisibility) R.drawable.ic_eye else R.drawable.ic_eye_closed

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = state.password,
            onValueChange = { password ->
                loginInteraction.updatePasswordState(password)
            },
            placeholder = { Text("", color = Color.Black.copy(alpha = 0.6f)) },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = colors.card,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = Color.Black
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(painter = painterResource(id = passwordIcon), contentDescription = null)
                }
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 24.dp, end = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                stringResource(R.string.forget_password),
                fontSize = 14.sp,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.clickable { navigateToForgetPassword() }
            )
        }
        Button(
            onClick = {
                loginInteraction.login(state.email, state.password)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp),
            colors = colors
        ) {
            Text(
                text = stringResource(R.string.sign_in),
                style = MaterialTheme.typography.bodyLarge,
                color = colors.onPrimary
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LogInPreview() {
    val viewModel: LoginViewModel = hiltViewModel()
    LoginContent(viewModel, {}, LoginUiState())
}
