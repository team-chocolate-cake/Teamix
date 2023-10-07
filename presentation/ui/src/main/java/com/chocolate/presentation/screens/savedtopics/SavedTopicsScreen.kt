package com.chocolate.presentation.screens.savedtopics

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
import com.chocolate.presentation.composable.EmptyDataWithBoxLottie
import com.chocolate.presentation.composable.SwipeCard
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.screens.createchannel.composable.ActionSnakeBar
import com.chocolate.presentation.screens.savedtopics.composable.SavedTopicsCard
import com.chocolate.presentation.theme.SpacingMedium
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.savedTopics.SavedTopicsInteraction
import com.chocolate.viewmodel.savedTopics.SavedTopicsUiState
import com.chocolate.viewmodel.savedTopics.SavedTopicsViewModel

@Composable
fun SavedTopicsScreen(
    viewModel: SavedTopicsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    SavedTopicsContent(state, viewModel)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SavedTopicsContent(state: SavedTopicsUiState, savedTopicsInteraction: SavedTopicsInteraction) {
    val colors = MaterialTheme.customColors()
    TeamixScaffold(
        hasAppBar = true,
        hasBackArrow = true,
        containerColorAppBar = colors.card,
        title = stringResource(R.string.saved_topics)
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(SpacingXLarge),
            verticalArrangement = Arrangement.spacedBy(SpacingMedium)
        ) {
            items(state.topics) { savedTopicsItem ->
                SwipeCard(
                    modifier = Modifier.animateItemPlacement(),
                    itemId = savedTopicsItem.id,
                    cardItem = {
                        SavedTopicsCard(topic = savedTopicsItem)
                    },
                    onClickDismiss = { savedTopicsInteraction.onDismissTopic(it) })
            }
        }

        AnimatedVisibility(visible = state.isLoading, modifier = Modifier.padding(padding)) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator(color = colors.primary) }
        }

        EmptyDataWithBoxLottie(
            isShow = state.topics.isEmpty() && !state.isLoading,
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = SpacingXLarge),
            isPlaying = true,
            title = stringResource(id = R.string.no_saved_items),
            subTitle = stringResource(id = R.string.no_saved_items_body)
        )

        if (state.error != null && state.deleteStateTopic == null) {
            ActionSnakeBar(
                isVisible = true,
                contentMessage = state.error.toString(),
                isToggleButtonVisible = false,
                actionTitle = stringResource(id = R.string.dismiss)
            )
        }

        if (state.error == null && state.deleteStateTopic != null) {
            ActionSnakeBar(
                isVisible = true,
                contentMessage = state.deleteStateTopic.toString(),
                isToggleButtonVisible = false,
                actionTitle = stringResource(id = R.string.dismiss)
            )
        }


    }
}