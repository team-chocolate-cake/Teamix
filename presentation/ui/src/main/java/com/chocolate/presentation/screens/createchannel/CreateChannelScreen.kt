package com.chocolate.presentation.screens.createchannel

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.composable.TeamixTextField
import com.chocolate.presentation.screens.choosemember.navigateToChooseMember
import com.chocolate.presentation.screens.createchannel.composable.ActionSnakeBar
import com.chocolate.presentation.screens.createchannel.composable.ToggleButton
import com.chocolate.presentation.theme.Height56
import com.chocolate.presentation.theme.SpacingHuge
import com.chocolate.presentation.theme.SpacingMedium
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.CollectUiEffect
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.presentation.util.hideKeyboard
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
    val context = LocalContext.current
    val rootView = LocalView.current
    TeamixScaffold(
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
                modifier = Modifier.padding(bottom = SpacingMedium),
                text = stringResource(id = R.string.channel_name),
                style = textStyle.labelMedium,
                color = colors.onBackground87,
                textAlign = TextAlign.Start
            )

            TeamixTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Height56),
                value = state.channelName,
                onValueChange = { createChannelInteraction.onChannelNameTextChange(it) },
                keyboardActions = KeyboardActions(onDone = { hideKeyboard(context, rootView) }),
                singleLine = true,
            )

            Text(
                modifier = Modifier.padding(bottom = SpacingMedium, top = SpacingXLarge),
                text = stringResource(id = R.string.channel_description),
                style = textStyle.labelMedium,
                color = colors.onBackground87,
                textAlign = TextAlign.Start
            )

            TeamixTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Height56),
                value = state.description ?: "",
                minLines = 3,
                onValueChange = { createChannelInteraction.onChannelDescriptionChange(it) },
                keyboardActions = KeyboardActions(onDone = { hideKeyboard(context, rootView) }),
                singleLine = true,
            )

            ToggleButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = SpacingHuge)
                    .align(alignment = Alignment.End),
                color = colors.primary,
                isFilled = true,
                onClick = {
                    createChannelInteraction.onNextClicked()
                    hideKeyboard(context, rootView)
                }
            ) {
                Text(
                    modifier = Modifier.padding(bottom = SpacingMedium, top = SpacingXLarge),
                    text = stringResource(id = R.string.next),
                    style = textStyle.bodyLarge,
                    color = colors.white,
                    textAlign = TextAlign.Center
                )
            }

        }
        state.errorMessage?.let {
            ActionSnakeBar(
                contentMessage = it,
                isVisible = true,
                isToggleButtonVisible = false,
                onDismiss = { createChannelInteraction.onSnackBarDismiss() }
            )
        }
    }
}