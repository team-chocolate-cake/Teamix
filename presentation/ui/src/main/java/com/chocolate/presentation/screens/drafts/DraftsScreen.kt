package com.chocolate.presentation.screens.drafts

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.DraftCard
import com.chocolate.presentation.composable.EmptyDataWithBoxLottie
import com.chocolate.presentation.composable.NoInternetLottie
import com.chocolate.presentation.composable.SwipeCard
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.draft.DraftInteraction
import com.chocolate.viewmodel.draft.DraftViewModel
import com.chocolate.viewmodel.draft.DraftsUiState

@Composable
fun DraftsScreen(
    viewModel: DraftViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    DraftsContent(state, viewModel)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DraftsContent(state: DraftsUiState, draftInteraction: DraftInteraction) {
    val colors = MaterialTheme.customColors()
    TeamixScaffold(
        isDarkMode = state.isDarkModel,
        hasAppBar = true,
        hasBackArrow = true,
        containerColorAppBar = colors.card,
        title = stringResource(id = R.string.drafts),
        error = state.error,
        onRetry = { draftInteraction.onClickRetry() },
        onError = {
            NoInternetLottie(
                text = stringResource(id = R.string.no_internet_connection),
                isShow = state.error != null,
                isDarkMode = state.isDarkModel,
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(SpacingXLarge),
            verticalArrangement = Arrangement.spacedBy(SpacingXMedium)
        ) {
            items(state.draftItems, key = { it.id }) {
                SwipeCard(
                    modifier = Modifier.animateItemPlacement(),
                    messageId = it.id,
                    cardItem = {
                        DraftCard(
                            id = it.id,
                            time = it.formattedTime,
                            messageContent = it.messageContent,
                            topicName = it.topicName,
                            isInStream = it.isInStream,
                            onClickMessage = {}
                        )
                    },
                    onClickDismiss = { draftInteraction.deleteDraft(it) })
            }
        }
                EmptyDataWithBoxLottie(
                    modifier = Modifier.padding(padding),
                    isPlaying = true,
                    isShow = state.draftItems.isEmpty() && !state.isLoading,
                    title = stringResource(R.string.draft_messages_to_send_when_you_re_ready),
                    subTitle = stringResource(R.string.sub_title_empty_data)
                )


        AnimatedVisibility(visible = state.isLoading, modifier = Modifier.padding(padding)) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator(color = colors.primary) }
        }
    }
}