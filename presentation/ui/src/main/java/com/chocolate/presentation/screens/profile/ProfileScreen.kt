package com.chocolate.presentation.screens.profile

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.MultiChoiceDialog
import com.chocolate.presentation.composable.ProfileDialog
import com.chocolate.presentation.composable.ProfileHorizontalPager
import com.chocolate.presentation.composable.ProfileImage
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.screens.organiztion.navigateToOrganizationName
import com.chocolate.presentation.theme.BoxHeight440
import com.chocolate.presentation.theme.ButtonSize110
import com.chocolate.presentation.theme.Radius16
import com.chocolate.presentation.theme.Radius24
import com.chocolate.presentation.theme.RowWidth250
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space26
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.CollectUiEffect
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.presentation.util.updateResources
import com.chocolate.viewmodel.main.MainViewModel
import com.chocolate.viewmodel.profile.LocalLanguage
import com.chocolate.viewmodel.profile.ProfileEffect
import com.chocolate.viewmodel.profile.ProfileInteraction
import com.chocolate.viewmodel.profile.ProfileUiState
import com.chocolate.viewmodel.profile.ProfileViewModel
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val navController = LocalNavController.current
    val state by viewModel.state.collectAsState()
    val darkThemeState by mainViewModel.state.collectAsState()
    val colors = MaterialTheme.customColors()
    val context = LocalContext.current
    val pageState = rememberPagerState(initialPage = 0)
    val scrollState = rememberScrollState()
    CollectUiEffect(viewModel) { effect ->
        when (effect) {
            ProfileEffect.NavigateToOrganizationScreen -> {
                navController.navigateToOrganizationName()
            }

            ProfileEffect.NavigateToSearchScreen -> {
                // navigate to search screen
            }
        }
    }
    if (!state.isLoading) {
        ProfileContent(
            state = state,
            darkThemeState = darkThemeState,
            mainViewModel = mainViewModel,
            profileInteraction = viewModel,
            onUpdateAppLanguage = { newLanguage ->
                val languageCode = state.languageMap[newLanguage] ?: "en"
                viewModel.updateAppLanguage(languageCode)
                updateResources(context = context, localeLanguage = Locale(languageCode))
            },
            pageState = pageState,
            scrollState = scrollState,
        )
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(color = colors.primary)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ProfileContent(
    state: ProfileUiState,
    darkThemeState: Boolean,
    mainViewModel: MainViewModel,
    onUpdateAppLanguage: (newLanguage: String) -> Unit,
    profileInteraction: ProfileInteraction,
    pageState: PagerState,
    scrollState: ScrollState
) {
    val color = MaterialTheme.customColors()
    val context = LocalContext.current
    val typography = MaterialTheme.typography

    LaunchedEffect(state.pagerNumber) {
        pageState.animateScrollToPage(state.pagerNumber)
    }

    AnimatedVisibility(state.showLanguageDialog) {
        MultiChoiceDialog(
            onClickDone = {
                profileInteraction.updateLanguageDialogState(false)
                mainViewModel.restart(context)
            },
            whenChoice = { newLanguage -> onUpdateAppLanguage(newLanguage) },
            choices = state.languageMap.keys.toList(),
            oldSelectedChoice = when (state.lastAppLanguage) {
                state.languageMap[LocalLanguage.Arabic.name] -> {
                    LocalLanguage.Arabic.name
                }

                state.languageMap[LocalLanguage.Chinese.name] -> {
                    LocalLanguage.Chinese.name
                }

                state.languageMap[LocalLanguage.Spanish.name] -> {
                    LocalLanguage.Spanish.name
                }

                else -> {
                    LocalLanguage.English.name
                }
            }
        )
    }
    AnimatedVisibility(state.showWarningDialog) {
        ProfileDialog(title = stringResource(R.string.warning),
            text = stringResource(R.string.waring_details),
            onDismissButtonClick = { profileInteraction.onRevertChange() },
            onConfirmButtonClick = { profileInteraction.onUserInformationFocusChange() }
        )
    }
    AnimatedVisibility(state.showLogoutDialog) {
        ProfileDialog(
            title = stringResource(R.string.logout_title),
            text = stringResource(R.string.logout_content_message),
            onDismissButtonClick = { profileInteraction.updateLogoutDialogState(false) },
            onConfirmButtonClick = {
                profileInteraction.updateLogoutDialogState(false)
                profileInteraction.onLogoutButtonClicked()
            },
        )
    }

    TeamixScaffold(isDarkMode = darkThemeState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color.background)
                .padding(top = Space26)
                .verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileImage(state)
            Text(
                state.name, modifier = Modifier.padding(top = Space16),
                style = typography.titleMedium,
                color = color.onBackground87
            )
            Text(
                state.email,
                style = typography.titleMedium,
                color = color.onBackground60
            )
            Text(
                state.role,
                style = typography.titleMedium,
                color = color.onBackground60
            )

            Spacer(modifier = Modifier.weight(1f))

            Box(
                Modifier
                    .padding(horizontal = Space16)
                    .padding(bottom = Space16)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(Radius16))
                    .height(BoxHeight440)
                    .background(color.card)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = Space16),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .width(RowWidth250)
                            .clip(RoundedCornerShape(Radius24))
                            .background(color.background),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = { profileInteraction.onClickProfileButton() },
                            modifier = Modifier
                                .padding(start = Space8)
                                .width(ButtonSize110),
                            colors = ButtonDefaults.buttonColors(
                                if (pageState.currentPage == 0) color.primary.copy(alpha = 1f) else
                                    color.background
                            )
                        ) {
                            Text(
                                text = stringResource(R.string.profile),
                                color = if (pageState.currentPage == 0) color.onPrimary else color.onBackground60
                            )
                        }
                        Button(
                            onClick = {
                                if (profileInteraction.areUserDataEqual()) {
                                    profileInteraction.updateWarningDialog(true)
                                } else {
                                    profileInteraction.onClickSettingsButton()
                                }
                            },
                            modifier = Modifier
                                .padding(end = Space8)
                                .width(ButtonSize110),
                            colors = ButtonDefaults.buttonColors(
                                if (pageState.currentPage == 1) color.primary.copy(alpha = 1f) else
                                    color.background
                            )
                        ) {
                            Text(
                                text = stringResource(R.string.settings),
                                color = if (pageState.currentPage == 1) color.onPrimary else color.onBackground60,
                                maxLines = 1
                            )
                        }
                    }

                    ProfileHorizontalPager(
                        pageState = pageState,
                        state = state,
                        profileInteraction = profileInteraction,
                        mainViewModel = mainViewModel,
                        darkThemeState = darkThemeState,
                        context = context
                    )

                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}