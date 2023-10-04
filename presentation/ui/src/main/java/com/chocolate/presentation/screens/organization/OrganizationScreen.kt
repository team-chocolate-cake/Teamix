package com.chocolate.presentation.screens.organization

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.SeparatorWithText
import com.chocolate.presentation.composable.TeamixButton
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.composable.TeamixTextField
import com.chocolate.presentation.screens.createchannel.composable.ActionSnakeBar
import com.chocolate.presentation.screens.createorganization.navigateToCreateOrganization
import com.chocolate.presentation.screens.login.navigateToLogin
import com.chocolate.presentation.screens.welcome.navigateToWelcome
import com.chocolate.presentation.theme.DarkCard
import com.chocolate.presentation.theme.Float1
import com.chocolate.presentation.theme.Height56
import com.chocolate.presentation.theme.SpacingExtraHuge
import com.chocolate.presentation.theme.SpacingGigantic
import com.chocolate.presentation.theme.SpacingHuge
import com.chocolate.presentation.theme.SpacingMedium
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXXHuge
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.CollectUiEffect
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.presentation.util.hideKeyboard
import com.chocolate.viewmodel.organizationname.OrganizationNameInteraction
import com.chocolate.viewmodel.organizationname.OrganizationNameUiEffect
import com.chocolate.viewmodel.organizationname.OrganizationNameUiState
import com.chocolate.viewmodel.organizationname.OrganizationNameViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun OrganizationScreen(
    viewModel: OrganizationNameViewModel = hiltViewModel(),
) {
    val navController = LocalNavController.current
    val state by viewModel.state.collectAsState()
    CollectUiEffect(viewModel.effect) { effect ->
        when (effect) {
            OrganizationNameUiEffect.NavigateToCreateOrganization -> navController.navigateToCreateOrganization()
            OrganizationNameUiEffect.NavigateToLoginScreen -> navController.navigateToLogin(
                organizationName = state.organizationName
            )

            OrganizationNameUiEffect.ShowSnackBar -> {}
        }
    }
    AnimatedVisibility(state.onboardingState) {
        OrganizationContent(
            organizationNameInteraction = viewModel,
            state = state
        )
    }
    if (state.onboardingState.not()) {
        navController.navigateToWelcome()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OrganizationContent(
    organizationNameInteraction: OrganizationNameInteraction,
    state: OrganizationNameUiState,
) {
    val colors = MaterialTheme.customColors()
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val rootView = LocalView.current
    val systemUiController = rememberSystemUiController()
    val isDarkTheme = colors.card == DarkCard
    TeamixScaffold {
        systemUiController.setSystemBarsColor(color = colors.transparent, darkIcons = !isDarkTheme)
        systemUiController.setNavigationBarColor(colors.black)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background)
                .verticalScroll(scrollState)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_start_organization),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = SpacingXXHuge)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = SpacingXLarge)
                    .padding(top = SpacingExtraHuge),
                text = stringResource(R.string.enter_your_name_organization),
                style = MaterialTheme.typography.labelMedium,
                color = colors.onBackground87
            )
            TeamixTextField(
                modifier = Modifier
                    .padding(horizontal = SpacingXLarge)
                    .padding(bottom = SpacingHuge)
                    .padding(top = SpacingMedium)
                    .fillMaxWidth()
                    .height(Height56),
                value = state.organizationName,
                onValueChange = { nameOrganization ->
                    organizationNameInteraction.onOrganizationNameChange(nameOrganization)
                },
                containerColor = colors.card,
                visualTransformation = VisualTransformation.None,
                keyboardOptions = KeyboardOptions.Default,
                keyboardActions = KeyboardActions(onDone = { hideKeyboard(context, rootView) }),
                singleLine = true,
            )
            TeamixButton(
                onClick = {
                    hideKeyboard(context, rootView)
                    organizationNameInteraction.onEnterButtonClick(state.organizationName)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(SpacingGigantic)
                    .padding(horizontal = SpacingXLarge),
                colors = colors,
            ) {
                AnimatedVisibility(state.isLoading) {
                    CircularProgressIndicator(
                        color = colors.card,
                        modifier = Modifier
                            .size(SpacingHuge)
                            .align(Alignment.CenterVertically)
                    )
                }
                AnimatedVisibility(visible = state.isLoading.not()) {
                    Text(
                        text = stringResource(R.string.enter),
                        style = MaterialTheme.typography.bodyLarge,
                        color = colors.onPrimary
                    )
                }
            }
            SeparatorWithText(
                modifier = Modifier.padding(bottom = SpacingMedium, top = SpacingExtraHuge)
            )
            Text(
                text = stringResource(R.string.create_new_organizat),
                color = colors.primary,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = { organizationNameInteraction.onClickCreateOrganization() }
                    )
                    .padding(bottom = SpacingHuge),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(Float1))
            state.error?.let {
                ActionSnakeBar(
                    contentMessage = it,
                    isVisible = true,
                    isToggleButtonVisible = false,
                    onDismiss = {
                        organizationNameInteraction.clearError()
                    }
                )
            }
        }
    }
}