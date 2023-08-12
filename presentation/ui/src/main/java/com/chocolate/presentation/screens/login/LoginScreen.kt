package com.chocolate.presentation.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chocolate.presentation.R
import com.chocolate.presentation.screens.home.navigateToHome
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.login.LoginUiState
import com.chocolate.viewmodel.login.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val state by loginViewModel.state.collectAsState()
    LoginContent(
        updateEmailState = loginViewModel::updateEmailState,
        updatePasswordState = loginViewModel::updatePasswordState,
        login = loginViewModel::login,
        navigateToHome = { navController.navigateToHome() },
        state = state
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(
    updateEmailState: (String) -> Unit,
    updatePasswordState: (String) -> Unit,
    login: (String, String) -> Unit,
    navigateToHome: () -> Unit,
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
                updateEmailState(email)
            },
            placeholder = { Text("", color = Color.Black.copy(alpha = 0.6f)) },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = colors.card,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            )
        )

        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.password),
            style = MaterialTheme.typography.labelMedium
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = state.password,
            onValueChange = { password ->
                updatePasswordState(password)
            },
            placeholder = { Text("", color = Color.Black.copy(alpha = 0.6f)) },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = colors.card,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            )
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
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp),
            onClick = {
                login(state.email, state.password)
                navigateToHome()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.primary
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(stringResource(R.string.sign_in))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LogInPreview() {
    LoginContent({}, {}, { _, _ -> }, {}, LoginUiState())
}
