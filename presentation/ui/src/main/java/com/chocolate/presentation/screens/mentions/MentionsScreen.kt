package com.chocolate.presentation.screens.mentions

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
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
import com.chocolate.presentation.screens.mentions.composables.MentionDays
import com.chocolate.presentation.screens.mentions.composables.SecondaryAppBar
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.mentions.MentionsViewModel
import com.chocolate.viewmodel.mentions.state.MentionsUiState

@Composable
fun MentionsScreen(
    viewModel: MentionsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    MentionsContent(
        state = state
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MentionsContent(
    state: MentionsUiState
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.customColors().background),
    ) {
        stickyHeader {
            SecondaryAppBar(stringResource(R.string.mentions))
        }
        items(state.days) {
            MentionDays(it){}
        }
    }
}