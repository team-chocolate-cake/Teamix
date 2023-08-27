package com.chocolate.presentation.screens.saveLater

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.DraftCard
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.saveLater.SaveLaterMessageUiState
import com.chocolate.viewmodel.saveLater.SaveLaterViewModel

@Composable
fun SaveLaterScreen(
    viewModel: SaveLaterViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    SaveLaterContent(state)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SaveLaterContent(state: SaveLaterMessageUiState) {
    val colors = MaterialTheme.customColors()
    TeamixScaffold(
        isDarkMode = isSystemInDarkTheme(),
        hasAppBar = true,
        hasBackArrow = true,
        containerColorAppBar = colors.card,
        title = stringResource(id = R.string.savedlater),
        isLoading = state.isLoading,
        error = state.error,
        ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(SpacingXLarge),
            verticalArrangement = Arrangement.spacedBy(SpacingXMedium)
        ) {
            items(state.messages, key = { it.id }) {
                DraftCard(
                    id = it.id,
                    time = it.time,
                    messageContent = it.messageContent,
                    onClickMessage = {},
                    modifier = Modifier.animateItemPlacement(),
                )
            }
        }
    }
}
