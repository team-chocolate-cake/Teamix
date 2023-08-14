package com.chocolate.presentation.screens.organiztion

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.Button
import com.chocolate.presentation.screens.create_organization.navigateToCreateOrganization
import com.chocolate.presentation.screens.login.navigateToLogin
import com.chocolate.presentation.screens.welcome.navigateToWelcome
import com.chocolate.presentation.theme.LightBackground
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space32
import com.chocolate.presentation.theme.Space48
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.organization_name.OrganizationNameInteraction
import com.chocolate.viewmodel.organization_name.OrganizationNameUiEffect
import com.chocolate.viewmodel.organization_name.OrganizationNameUiState
import com.chocolate.viewmodel.organization_name.OrganizationNameViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun OrganizationScreen(
    navController: NavController,
    viewModel: OrganizationNameViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                OrganizationNameUiEffect.NavigateToCreateOrganization -> navController.navigateToCreateOrganization()
                OrganizationNameUiEffect.NavigateToLoginScreen -> navController.navigateToLogin()
            }
        }
    }
    if (state.onboardingState) {
        OrganizationContent(
            organizationNameInteraction = viewModel,
            state = state,
        )
    } else {
        LaunchedEffect(Unit) { navController.navigateToWelcome() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrganizationContent(
    organizationNameInteraction: OrganizationNameInteraction,
    state: OrganizationNameUiState
) {
    val colors = MaterialTheme.customColors()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightBackground)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.start__5_),
                contentDescription = null,
                modifier = Modifier.padding(top = 28.dp)
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = Space32),
                text = stringResource(R.string.enter_your_name_organization),
                style = MaterialTheme.typography.labelMedium,
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 24.dp)
                    .padding(top = Space8),
                value = state.organizationName,
                onValueChange = { nameOrganization ->
                    organizationNameInteraction.onOrganizationNameChange(nameOrganization)
                },
                placeholder = { Text("", color = Color.Black.copy(alpha = 0.6f)) },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = colors.black
                )
            )
            Button(
                onClick = {
                    organizationNameInteraction.onClickActionButton(state.organizationName)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Space48)
                    .padding(horizontal = Space16),
                colors = colors,
                enabled = state.organizationName.isNotEmpty()
            ) {
                Text(
                    text = stringResource(R.string.enter),
                    style = MaterialTheme.typography.bodyLarge,
                    color = colors.onPrimary
                )
            }
            SeparatorWithText(modifier = Modifier.padding(bottom = Space8, top = Space32))
            Text(
                text = stringResource(R.string.create_new_organizat),
                color = colors.primary,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { organizationNameInteraction.onClickCreateOrganization() },
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SeparatorWithText(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Space16),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(Color.Gray)
                .padding(horizontal = Space8)
        )
        Text(
            text = "OR",
            color = Color.Gray,
            modifier = Modifier
                .background(Color.Transparent, shape = RoundedCornerShape(4.dp))
                .padding(horizontal = Space8)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(Color.Gray)
                .padding(horizontal = Space8)
        )
    }
}