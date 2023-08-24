package com.chocolate.presentation.screens.profile

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.MultiChoiceDialog
import com.chocolate.presentation.composable.NoInternetLottie
import com.chocolate.presentation.composable.ProfileDialog
import com.chocolate.presentation.composable.ProfileSettingsScreen
import com.chocolate.presentation.composable.ProfileTextField
import com.chocolate.presentation.screens.organiztion.navigateToOrganizationName
import com.chocolate.presentation.theme.BoxHeight440
import com.chocolate.presentation.theme.ButtonSize110
import com.chocolate.presentation.theme.ImageSize110
import com.chocolate.presentation.theme.ImageSize130
import com.chocolate.presentation.theme.ImageSize158
import com.chocolate.presentation.theme.Radius16
import com.chocolate.presentation.theme.Radius24
import com.chocolate.presentation.theme.RowWidth250
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space26
import com.chocolate.presentation.theme.Space32
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController
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

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
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
    val systemUiController = rememberSystemUiController()
    val typography = MaterialTheme.typography
    key(state.showThemeDialog) {
        systemUiController.setStatusBarColor(
            MaterialTheme.customColors().background, darkIcons = !mainViewModel.state.value
        )
        systemUiController.setNavigationBarColor(Color.Black)
    }

    LaunchedEffect(state.pagerNumber) {
        pageState.animateScrollToPage(state.pagerNumber)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color.background)
            .padding(top = Space26)
            .verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(Modifier.height(ImageSize158)) {
            Box(
                modifier = Modifier
                    .padding(top = Space32)
                    .size(ImageSize130)
                    .clip(CircleShape)
                    .border(2.dp, color.primary, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                val imageRequest = ImageRequest.Builder(context)
                    .data(state.imageUrl)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .transformations(CircleCropTransformation())
                    .build()
                val imageLoader =
                    ImageLoader.Builder(context).respectCacheHeaders(false).build()

                Image(
                    modifier = Modifier
                        .size(ImageSize110)
                        .clip(CircleShape),
                    painter = rememberAsyncImagePainter(imageRequest, imageLoader),
                    contentDescription = null
                )
            }
        }

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

        AnimatedVisibility(state.showLanguageDialog) {
            MultiChoiceDialog(
                onDismissRequest = { profileInteraction.updateLanguageDialogState(false) },
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
        AnimatedVisibility(state.showClearHistoryDialog) {
            ProfileDialog(
                title = stringResource(R.string.clear_history_title),
                text = stringResource(R.string.clear_history_text),
                onDismissButtonClick = { profileInteraction.updateClearHistoryState(false) },
                onConfirmButtonClick = { },
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
                        onClick = { profileInteraction.onClickProfileButton() }, modifier = Modifier
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

                HorizontalPager(
                    modifier = Modifier.padding(top = Space8),
                    state = pageState,
                    pageCount = 2,
                    userScrollEnabled = false
                ) {
                    if (pageState.currentPage == 0) {
                        LazyColumn(contentPadding = PaddingValues(all = Space16)) {
                            item {
                                val keyboardController = LocalSoftwareKeyboardController.current
                                ProfileTextField(
                                    text = state.name,
                                    onValueChange = { username ->
                                        profileInteraction.onUsernameChange(username)
                                    },
                                    onDone = {
                                        keyboardController?.hide()
                                        profileInteraction.onUserInformationFocusChange()
                                    },
                                    colorFocused = color.primary,
                                    colorUnFocused = color.background,
                                    colorIcon = color.primary
                                )
                                ProfileTextField(
                                    text = state.email,
                                    onValueChange = { email ->
                                        profileInteraction.onEmailChange(email)
                                    },
                                    onDone = {
                                        keyboardController?.hide()
                                        profileInteraction.onUserInformationFocusChange()
                                    },
                                    colorFocused = color.primary,
                                    colorUnFocused = color.background,
                                    colorIcon = color.primary
                                )
                            }
                        }
                    } else {
                        ProfileSettingsScreen(
                            color = color,
                            mainViewModel = mainViewModel,
                            darkThemeState = darkThemeState,
                            profileInteraction = profileInteraction,
                        )
                    }
                    state.error?.let {
                        Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                    }
                    state.message?.let {
                        Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
                    }
                }
                NoInternetLottie(
                    text = stringResource(id = R.string.no_internet_connection),
                    isShow = state.showNoInternetLottie,
                    isDarkMode = mainViewModel.state.value,
                    onClickRetry = { profileInteraction.onClickRetryToGetPersonalInformation() }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}