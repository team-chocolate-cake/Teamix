package com.chocolate.presentation.screens.create_organization

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.R
import com.chocolate.presentation.Screen
import com.chocolate.presentation.composable.SeparatorWithText
import com.chocolate.presentation.composable.TeamixButton
import com.chocolate.presentation.composable.TeamixImagePicker
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.composable.TeamixTextField
import com.chocolate.presentation.screens.createMember.navigateToCreateMember
import com.chocolate.presentation.screens.createChannel.composable.ActionSnakeBar
import com.chocolate.presentation.screens.organiztion.navigateToOrganizationName
import com.chocolate.presentation.theme.SpacingExtraHuge
import com.chocolate.presentation.theme.SpacingGigantic
import com.chocolate.presentation.theme.SpacingMegaGigantic
import com.chocolate.presentation.theme.SpacingSuperMassive
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.SpacingXXLarge
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.CollectUiEffect
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.createOrganization.CreateOrganizationInteraction
import com.chocolate.viewmodel.createOrganization.CreateOrganizationUiEffect
import com.chocolate.viewmodel.createOrganization.CreateOrganizationUiState
import com.chocolate.viewmodel.createOrganization.CreateOrganizationViewModel


@Composable
fun CreateOrganizationScreen(
    viewModel: CreateOrganizationViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val state by viewModel.state.collectAsState()
    CollectUiEffect(viewModel.effect) { effect ->
        when (effect) {
            is CreateOrganizationUiEffect.NavigateToCreateMemberScreen ->
                navController.navigateToCreateMember(effect.role)

            CreateOrganizationUiEffect.NavigateToHaveOrganization ->
                navController.navigateToOrganizationName {
                    popUpTo(Screen.CreateOrganization.route) {
                        inclusive = true
                    }
                }
        }
    }
    CreateOrganizationContent(
        createOrganizationInteraction = viewModel,
        state = state
    )
}

@Composable
fun CreateOrganizationContent(
    createOrganizationInteraction: CreateOrganizationInteraction,
    state: CreateOrganizationUiState,
) {
    val colors = MaterialTheme.customColors()
    val scrollState = rememberScrollState()
    TeamixScaffold(isDarkMode = isSystemInDarkTheme()) {
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            TeamixImagePicker(createOrganizationInteraction::onOrganizationImageChange, modifier = Modifier.padding(top=SpacingSuperMassive))
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = SpacingMegaGigantic),
                text = stringResource(R.string.enter_your_name_organization),
                style = MaterialTheme.typography.labelMedium,
                color = colors.onBackground87
            )
            TeamixTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 24.dp)
                    .padding(top = SpacingXMedium),
                value = state.organizationName,
                onValueChange = { nameOrganization ->
                    createOrganizationInteraction.onOrganizationNameChange(nameOrganization)
                },
                containerColor = colors.card,
                visualTransformation = VisualTransformation.None,
                keyboardOptions = KeyboardOptions.Default,
            )
            TeamixButton(
                onClick = {
                    createOrganizationInteraction.onClickNextButton()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(SpacingGigantic)
                    .padding(horizontal = SpacingXLarge),
                colors = colors,
            ) {
                AnimatedVisibility(state.isLoading) { CircularProgressIndicator(color = colors.card) }
                AnimatedVisibility(visible = true) {
                    Text(
                        text = stringResource(R.string.next),
                        style = MaterialTheme.typography.bodyLarge,
                        color = colors.onPrimary
                    )
                }
            }
            SeparatorWithText(
                modifier = Modifier.padding(bottom = SpacingXMedium, top = SpacingExtraHuge)
            )
            Text(
                text = stringResource(R.string.have_organization),
                color = colors.primary,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
                            createOrganizationInteraction.onClickHaveOrganization()
                        }
                    )
                    .padding(bottom = SpacingXXLarge),
                textAlign = TextAlign.Center
            )
            ActionSnakeBar(
                contentMessage = state.error.toString(),
                isVisible = true,
                isToggleButtonVisible = false
            )
        }
    }
}

@Composable
@Preview
fun CreateOrganizationPreview() {
    CreateOrganizationScreen()
}

