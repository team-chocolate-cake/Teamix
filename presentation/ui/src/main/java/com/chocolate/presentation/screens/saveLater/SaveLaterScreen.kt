package com.chocolate.presentation.screens.saveLater

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.EmptyDataWithBoxLottie
import com.chocolate.presentation.composable.SaveLaterCard
import com.chocolate.presentation.composable.SwipeCard
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.screens.create_channel.composable.ActionSnakeBar
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.saveLater.SaveLaterInteraction
import com.chocolate.viewmodel.saveLater.SaveLaterMessageUiState
import com.chocolate.viewmodel.saveLater.SaveLaterViewModel

@Composable
fun SaveLaterScreen(
    viewModel: SaveLaterViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    SaveLaterContent(state, viewModel)
}

@Composable
fun SaveLaterContent(state: SaveLaterMessageUiState, interaction: SaveLaterInteraction) {
    val colors = MaterialTheme.customColors()
    val context = LocalContext.current
    TeamixScaffold(
        isDarkMode = isSystemInDarkTheme(),
        hasAppBar = true,
        hasBackArrow = true,
        containerColorAppBar = colors.card,
        title = stringResource(id = R.string.savedlater),
        isLoading = state.isLoading,
        error = state.error,
    ) { padding ->
        Box {
            LazyColumn(
                modifier = Modifier.padding(padding),
                contentPadding = PaddingValues(SpacingXLarge),
                verticalArrangement = Arrangement.spacedBy(SpacingXMedium)
            ) {
                items(state.messages, key = { it.id }) { message ->
                    SwipeCard(
                        messageId = message.id,
                        onclickDismiss = interaction::onDismissMessage
                    ) {
                        SaveLaterCard(
                            item = message,
                            painter = rememberAsyncImagePainter(model = message.imageUrl)
                        )
                    }
                }
            }
            EmptyDataWithBoxLottie(
                modifier = Modifier.padding(padding),
                isPlaying = true,
                isShow = state.messages.isEmpty() && !state.isLoading,
                title = stringResource(R.string.no_saved_items),
                subTitle = stringResource(R.string.your_saved_items_will_appear_here_for_easy_access_and_reference)
            )

            val message = state.message
            val error = state.error
            if (message != null && error == null) {
                ActionSnakeBar(
                    contentMessage = message,
                    isVisible = true,
                    isToggleButtonVisible = false
                )
            }
            if (error != null && message == null) {
                ActionSnakeBar(
                    contentMessage = error,
                    isVisible = true,
                    isToggleButtonVisible = false
                )
            }
        }
    }
}
