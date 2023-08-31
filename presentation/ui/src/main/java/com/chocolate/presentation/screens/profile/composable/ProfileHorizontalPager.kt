package com.chocolate.presentation.screens.profile.composable

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.chocolate.presentation.composable.TeamixOutLinedTextField
import com.chocolate.presentation.screens.create_channel.composable.ActionSnakeBar
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.profile.ProfileInteraction
import com.chocolate.viewmodel.profile.ProfileUiState


@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun ProfileHorizontalPager(
    pageState: PagerState,
    state: ProfileUiState,
    profileInteraction: ProfileInteraction,
    context: Context
) {
    val color = MaterialTheme.customColors()
    HorizontalPager(
        modifier = Modifier.padding(top = SpacingXMedium),
        state = pageState,
        pageCount = 2,
        userScrollEnabled = false
    ) {
        Box {


            if (pageState.currentPage == 0) {
                LazyColumn(contentPadding = PaddingValues(all = SpacingXLarge)) {
                    item {
                        val keyboardController = LocalSoftwareKeyboardController.current
                        TeamixOutLinedTextField(
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
                        TeamixOutLinedTextField(
                            text = state.email,
                            onValueChange = {},
                            colorFocused = color.background,
                            colorUnFocused = color.background,
                            colorIcon = color.card,
                            readOnly = true
                        )
                    }
                }
            } else {
                ProfileSettingsPage(
                    color = color,
                    darkThemeState = state.isDarkTheme,
                    profileInteraction = profileInteraction,
                )
            }
            state.error?.let {
                ActionSnakeBar(
                    contentMessage = state.error.toString(),
                    isVisible = true,
                    isToggleButtonVisible = false
                )

            }
            state.message?.let {
                ActionSnakeBar(
                    contentMessage = state.message.toString(),
                    isVisible = true,
                    isToggleButtonVisible = false
                )

            }

        }
    }
}