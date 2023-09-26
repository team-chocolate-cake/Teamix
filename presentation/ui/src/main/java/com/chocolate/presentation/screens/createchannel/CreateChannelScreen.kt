package com.chocolate.presentation.screens.createchannel

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.composable.TeamixTextField
import com.chocolate.presentation.screens.choosemember.navigateToChooseMember
import com.chocolate.presentation.screens.createchannel.composable.ActionSnakeBar
import com.chocolate.presentation.screens.createchannel.composable.ToggleButton
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.SpacingXXLarge
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.CollectUiEffect
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.createchannel.CreateChannelInteraction
import com.chocolate.viewmodel.createchannel.CreateChannelUiEffect
import com.chocolate.viewmodel.createchannel.CreateChannelUiState
import com.chocolate.viewmodel.createchannel.CreateChannelViewModel

@Composable
fun CreateChannelScreen(
    createChannelViewModel: CreateChannelViewModel = hiltViewModel()
) {
    val state by createChannelViewModel.state.collectAsState()
    val navController = LocalNavController.current

    CollectUiEffect(createChannelViewModel.effect) { effect ->
        when (effect) {
            is CreateChannelUiEffect.NavigationToChooseMembers ->
                navController.navigateToChooseMember(
                    effect.channelName,
                    effect.description,
                    effect.isPrivate
                )
        }
    }

    CreateChannelContent(
        state = state,
        scrollState = rememberScrollState(),
        createChannelInteraction = createChannelViewModel
    )
}

@SuppressLint("SuspiciousIndentation")
@Composable
private fun CreateChannelContent(
    state: CreateChannelUiState,
    scrollState: ScrollState,
    createChannelInteraction: CreateChannelInteraction
) {
    val colors = MaterialTheme.customColors()
    val textStyle = MaterialTheme.typography

    TeamixScaffold(
        isDarkMode = isSystemInDarkTheme(),
        isLoading = state.isLoading,
        title = stringResource(id = R.string.create_channel),
        hasAppBar = true,
        containerColorAppBar = colors.card,
        hasBackArrow = true,
        onLoading = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator(color = colors.primary) }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colors.background)
                .verticalScroll(scrollState)
                .padding(paddingValues)
                .padding(SpacingXLarge)
        ) {
            Text(
                modifier = Modifier.padding(bottom = SpacingXMedium),
                text = stringResource(id = R.string.channel_name),
                style = textStyle.labelMedium,
                color = colors.onBackground87,
                textAlign = TextAlign.Start
            )

            TeamixTextField(
                value = state.channelName,
                onValueChange = { createChannelInteraction.onChannelNameTextChange(it) }
            )

            Text(
                modifier = Modifier.padding(bottom = SpacingXMedium, top = SpacingXLarge),
                text = stringResource(id = R.string.channel_description),
                style = textStyle.labelMedium,
                color = colors.onBackground87,
                textAlign = TextAlign.Start
            )

            TeamixTextField(
                value = state.description ?: "",
                singleLine = true,
                minLines = 3,
                onValueChange = { createChannelInteraction.onChannelDescriptionChange(it) }
            )

            Spacer(modifier = Modifier.weight(1f))

            ToggleButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = SpacingXXLarge)
                    .align(alignment = Alignment.End),
                color = colors.primary,
                isFilled = true,
                onClick = { createChannelInteraction.onNextClicked() }
            ) {
                Text(
                    modifier = Modifier.padding(bottom = SpacingXMedium, top = SpacingXLarge),
                    text = stringResource(id = R.string.next),
                    style = textStyle.bodyLarge,
                    color = colors.white,
                    textAlign = TextAlign.Center
                )
            }

        }

        ActionSnakeBar(
            contentMessage = state.message.toString(),
            isVisible = state.isError,
            isToggleButtonVisible = false,
        )

    }
}