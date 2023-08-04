package com.chocolate.presentation.mentions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.chocolate.presentation.R
import com.chocolate.presentation.mentions.composables.MentionDays
import com.chocolate.presentation.mentions.composables.SecondaryAppBar
import com.chocolate.presentation.theme.OnLightBackground
import com.chocolate.presentation.theme.Space16
import com.chocolate.viewmodel.mentions.MentionsViewModel
import com.chocolate.viewmodel.mentions.state.MentionsUiState

@Composable
fun MentionsScreen(
    viewModel: MentionsViewModel = MentionsViewModel()
) {
    val state by viewModel.state.collectAsState()
    MentionsContent(
        state = state
    )
}

@Composable
private fun MentionsContent(
    state: MentionsUiState
) {
    Column(
        modifier = Modifier.fillMaxSize().background(OnLightBackground)
    ) {
        SecondaryAppBar(stringResource(R.string.mentions))
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(Space16),
            verticalArrangement = Arrangement.spacedBy(Space16)
        ) {
            items(state.days) {
                MentionDays(it, onClick = {})
            }
        }
    }
}