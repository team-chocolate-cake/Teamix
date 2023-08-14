package com.chocolate.presentation.screens.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.TeamixSnackBar
import com.chocolate.presentation.screens.profile.composable.ChangeThemeDialog
import com.chocolate.presentation.screens.profile.composable.MultiChoiceDialog
import com.chocolate.presentation.screens.profile.composable.ProfileDialog
import com.chocolate.presentation.screens.profile.composable.ProfileTextField
import com.chocolate.presentation.screens.profile.composable.SettingCard
import com.chocolate.presentation.theme.BoxHeight440
import com.chocolate.presentation.theme.ButtonSize110
import com.chocolate.presentation.theme.IconSize24
import com.chocolate.presentation.theme.IconSize30
import com.chocolate.presentation.theme.ImageSize110
import com.chocolate.presentation.theme.ImageSize130
import com.chocolate.presentation.theme.ImageSize158
import com.chocolate.presentation.theme.Radius16
import com.chocolate.presentation.theme.Radius24
import com.chocolate.presentation.theme.RowWidth250
import com.chocolate.presentation.theme.Space1
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space24
import com.chocolate.presentation.theme.Space26
import com.chocolate.presentation.theme.Space32
import com.chocolate.presentation.theme.Thickness2
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.profile.ProfileEffect
import com.chocolate.viewmodel.profile.ProfileInteraction
import com.chocolate.viewmodel.profile.ProfileUiState
import com.chocolate.viewmodel.profile.ProfileViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val colors = MaterialTheme.customColors()
    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                ProfileEffect.NavigateToOwnerPower -> navController.navigateToOwnerPower()
            }
        }
    }
    if (!state.isLoading) {
        ProfileContent(
            state = state,
            profileInteraction = viewModel
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
@Composable
fun ProfileContent(
    state: ProfileUiState,
    profileInteraction: ProfileInteraction
    ){
    val color = MaterialTheme.customColors()
    var pageNumber by remember { mutableStateOf(0) }

    val pageState = rememberPagerState(initialPage = 0)

    LaunchedEffect(pageNumber) {
       pageState.animateScrollToPage(pageNumber)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color.background)
            .padding(top = Space26), horizontalAlignment = Alignment.CenterHorizontally
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
                Image(
                    modifier = Modifier
                        .size(ImageSize110)
                        .clip(CircleShape),
                    painter = rememberAsyncImagePainter(state.image),
                    contentDescription = null
                )
            }
            IconButton(
                onClick = { //open camera
                     },
                modifier = Modifier
                    .size(IconSize30)
                    .align(Alignment.BottomEnd)
                    .clip(CircleShape),
                colors = IconButtonDefaults.iconButtonColors(color.primary)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.camera),
                    contentDescription = null,
                    modifier = Modifier
                        .size(IconSize24)
                        .padding(bottom = Space1),
                    tint = color.onPrimary
                )
            }
        }

        Text(
            state.name, modifier = Modifier.padding(top = Space16),
            style = MaterialTheme.typography.titleMedium,
            color = color.onBackground87
        )
        Text(
            state.email, modifier = Modifier,
            style = MaterialTheme.typography.titleMedium,
            color = color.onBackground60
        )

        if (state.showLanguageDialog) {
            MultiChoiceDialog(
                { profileInteraction.updateLanguageDialogState(false) },
                listOf(
                    stringResource(R.string.english),
                    stringResource(R.string.arabic),
                    stringResource(R.string.french), stringResource(R.string.spanish)
                )
            )
        }
        if (state.showThemeDialog) {
            AlertDialog(
                modifier = Modifier,
                onDismissRequest = { profileInteraction.updateThemeDialogState(false) },
                confirmButton = {},
                dismissButton = {},
                text = {
                    ChangeThemeDialog()
                }, containerColor = color.background
            )
        }
        if (state.showClearHistoryDialog) {
            ProfileDialog(title = stringResource(R.string.clear_history_title),
                text =
                stringResource(R.string.clear_history_text),
                onClick = { profileInteraction.updateClearHistoryState(false) })

        }
        if (state.showLogoutDialog) {
            ProfileDialog(title = stringResource(R.string.logout_title), text =
            "", onClick = { profileInteraction.updateLogoutDialogState(false) })
        }
        Spacer(modifier = Modifier.weight(1f))

        Box(
            Modifier
                .padding(horizontal = Space16)
                .fillMaxWidth()
                .clip(RoundedCornerShape(Radius16, Radius16, 0.dp, 0.dp))
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
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { pageNumber = 0 }, modifier = Modifier
                            .padding(start = Space16)
                            .width(ButtonSize110),
                        colors = ButtonDefaults.buttonColors(
                            if (pageState.currentPage == 0) color.primary.copy(alpha = 1f) else
                                color.background
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.profile),
                            color = if (pageState.currentPage == 0) color.onPrimary else
                                color.onBackground60
                        )
                    }
                    Button(
                        onClick = { pageNumber = 1 }, modifier = Modifier
                            .padding(end = Space16)
                            .width(110.dp),
                        colors = ButtonDefaults.buttonColors(
                            if (pageState.currentPage == 1) color.primary.copy(alpha = 1f) else
                                color.background
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.settings),
                            color = if (pageState.currentPage == 1) color.onPrimary else
                                color.onBackground60,
                            maxLines = 1
                        )
                    }
                }

                HorizontalPager(state = pageState, pageCount = 2,
                    modifier = Modifier.padding(bottom = 8.dp)) {
                    if (pageState.currentPage == 0) {
                        LazyColumn(
                            modifier = Modifier.padding(
                                horizontal = Space16,
                                vertical = 16.dp
                            )
                        ) {
                            item {
                                ProfileTextField(
                                    text = state.name,
                                    onValueChange = { username ->
                                        profileInteraction.onUsernameChange(username)
                                    },
                                    onDone = {profileInteraction.onUsernameFocusChange()},
                                    colorFocused = color.primary,
                                    colorUnFocused = color.background,
                                    colorIcon = color.primary
                                )
                                ProfileTextField(
                                    text = state.email,
                                    onValueChange = {email ->
                                        
                                    },
                                    onDone = {profileInteraction.onEmailFocusChange()},
                                    colorFocused=color.primary,
                                    colorUnFocused=color.background,
                                    colorIcon = color.primary
                                )
                            }
                        }

                    } else {

                        LazyColumn(modifier = Modifier.padding(vertical = 16.dp)) {
                            item {
                                SettingCard(
                                    click = { profileInteraction.onClickOwnerPower() },
                                    text = stringResource(R.string.owner_powers),
                                    icon = painterResource(id = R.drawable.ownerpowers)
                                )
                                Divider(color = color.background, thickness =Thickness2)
                                SettingCard(
                                    click = { profileInteraction.updateLanguageDialogState(true) },
                                    text = stringResource(R.string.language),
                                    icon = painterResource(id = R.drawable.language)
                                )
                                Divider(color = color.background, thickness = Thickness2)
                                SettingCard(
                                    click = { profileInteraction.updateThemeDialogState(true) },
                                    text = stringResource(R.string.change_theme),
                                    icon = painterResource(id = R.drawable.changetheme)
                                )
                                Divider(color = color.background, thickness =Thickness2)
                                SettingCard(
                                    click = { profileInteraction.updateClearHistoryState(true) },
                                    text = stringResource(R.string.clear_history),
                                    icon = painterResource(id = R.drawable.clearhistory)
                                )
                                Divider(color = color.background, thickness = Thickness2)
                                SettingCard(
                                    click = { profileInteraction.updateLogoutDialogState(true) },
                                    text = stringResource(R.string.log_out),
                                    icon = painterResource(id = R.drawable.logout)
                                )
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        if (state.error != null) {
            TeamixSnackBar(
                text = state.error ?: stringResource(R.string.default_error_message),
                state = "Retry",
                onClickButton = { profileInteraction.onClickRetry() },
                modifier = Modifier.padding(bottom = Space24).padding(horizontal = 16.dp)
            )
        }
        if(state.message.isNotEmpty()){
            TeamixSnackBar(
                text = state.message,
                onClickButton = { },
                modifier = Modifier.padding(bottom = Space24).padding(horizontal = 16.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(rememberNavController())
}

