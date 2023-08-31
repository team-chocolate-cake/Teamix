package com.chocolate.presentation.screens.saveLater

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SaveLaterContent(state: SaveLaterMessageUiState, interaction: SaveLaterInteraction) {
    val colors = MaterialTheme.customColors()
    TeamixScaffold(
        isDarkMode = isSystemInDarkTheme(),
        hasAppBar = true,
        hasBackArrow = true,
        containerColorAppBar = colors.card,
        title = stringResource(id = R.string.savedlater),
        isLoading = state.isLoading,
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(SpacingXLarge),
            verticalArrangement = Arrangement.spacedBy(SpacingXMedium)
        ) {
            items(state.messages, key = { it.id }) { message ->
                SwipeCard(
                    modifier = Modifier.animateItemPlacement(),
                    messageId = message.id,
                    cardItem = {
                        SaveLaterCard(
                            item = message,
                            painter = rememberAsyncImagePainter(model = message.imageUrl)
                        )
                    },
                    onClickDismiss = { interaction.onDismissMessage(it) }
                )

            }
        }
        AnimatedVisibility(visible = state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = colors.primary)
            }
        }
        EmptyDataWithBoxLottie(
            modifier = Modifier.padding(padding),
            isShow = state.messages.isEmpty() && !state.isLoading,
            isPlaying = true,
            title = stringResource(id = R.string.no_saved_items),
            subTitle = stringResource(id = R.string.no_saved_items_body)
        )
        val deleteMessage = state.deleteStateMessage
        val error = state.error
        if (deleteMessage != null && error == null) {
            ActionSnakeBar(
                isVisible = true,
                contentMessage = deleteMessage,
                onClick = interaction::onDeleteStateDismiss,
                actionTitle = stringResource(id = R.string.dismiss)
            )
        }

        if (error != null && deleteMessage == null) {
            ActionSnakeBar(
                isVisible = true,
                contentMessage = error,
                onClick = interaction::onErrorDismiss,
                actionTitle = stringResource(id = R.string.dismiss)
            )
        }
    }
}
