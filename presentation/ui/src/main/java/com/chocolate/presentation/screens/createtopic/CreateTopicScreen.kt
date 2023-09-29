package com.chocolate.presentation.screens.createtopic

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.TeamixButton
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.composable.TeamixTextField
import com.chocolate.presentation.screens.createchannel.composable.ActionSnakeBar
import com.chocolate.presentation.screens.topicmessages.navigateToTopicMessage
import com.chocolate.presentation.theme.Float1
import com.chocolate.presentation.theme.LightCard
import com.chocolate.presentation.theme.SpacingHuge
import com.chocolate.presentation.theme.SpacingMedium
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.CollectUiEffect
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.presentation.util.hideKeyboard
import com.chocolate.viewmodel.createtopic.CreateTopicEffect
import com.chocolate.viewmodel.createtopic.CreateTopicInteraction
import com.chocolate.viewmodel.createtopic.CreateTopicUiState
import com.chocolate.viewmodel.createtopic.CreateTopicViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun CreateTopicScreen(
    createTopicViewModel: CreateTopicViewModel = hiltViewModel(),
    navController: NavController = LocalNavController.current
) {
    val state by createTopicViewModel.state.collectAsState()

    CollectUiEffect(createTopicViewModel.effect) { createTopicEffect ->
        when (createTopicEffect) {
            is CreateTopicEffect.NavigateToTopicScreen -> {
                navController.popBackStack()
                navController.navigateToTopicMessage(
                    channelId = createTopicEffect.channelId.toInt(),
                    topicId = createTopicEffect.topicId,
                    topicName = createTopicEffect.topicName,
                )
            }
        }
    }

    CreateChannelContent(
        state,
        createTopicViewModel,
    )

}

@Composable
fun CreateChannelContent(
    state: CreateTopicUiState,
    createTopicInteraction: CreateTopicInteraction,
) {
    val context = LocalContext.current
    val rootView = LocalView.current
    val colors = MaterialTheme.customColors()
    val systemUiController = rememberSystemUiController()
    val isDarkIcons = MaterialTheme.customColors().card == LightCard


    TeamixScaffold(
        hasBackArrow = true,
        title = stringResource(id = R.string.create_topic),
        hasAppBar = true,
        containerColorAppBar = colors.card,
        titleColor = colors.onBackground87,
    ) { padding ->
        systemUiController.setStatusBarColor(
            MaterialTheme.customColors().card,
            darkIcons = isDarkIcons
        )

        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .background(colors.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextInputField(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(SpacingXLarge),
                textInputLabel = stringResource(R.string.topic_name),
                onValueChange = createTopicInteraction::onTopicNameChange,
                value = state.name
            )

            TextInputField(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = SpacingXLarge, end = SpacingMedium, bottom = SpacingHuge),
                textInputLabel = stringResource(R.string.topic_content),
                onValueChange = createTopicInteraction::onTopicContentChange,
                value = state.content,

                )

            TeamixButton(
                onClick = {
                    createTopicInteraction.onCreateClick()
                    hideKeyboard(context, rootView)
                },
                colors = colors,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = SpacingXLarge)
            ) {
                AnimatedVisibility(visible = state.isLoading) {
                    CircularProgressIndicator(
                        color = colors.card,
                        modifier = Modifier
                            .size(SpacingHuge)
                            .align(Alignment.CenterVertically)
                    )
                }
                AnimatedVisibility(visible = !state.isLoading) {
                    Text(
                        text = stringResource(R.string.create),
                        style = MaterialTheme.typography.titleMedium,
                        color = colors.onPrimary
                    )
                }
            }
            Spacer(modifier = Modifier.weight(Float1))
            state.error?.let {
                ActionSnakeBar(
                    isVisible = true,
                    contentMessage = it,
                    onClick = createTopicInteraction::onErrorDismiss,
                    isToggleButtonVisible = false
                )
            }
        }
    }
}

@Preview
@Composable
fun CreateChannelContentPreview() {
    TeamixTheme {
        CreateTopicScreen()
    }
}

@Composable
private fun TextInputField(
    modifier: Modifier = Modifier,
    textInputLabel: String,
    onValueChange: (String) -> Unit,
    value: String
) {

    Column(modifier) {
        Text(
            text = textInputLabel,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(bottom = 8.dp),
            color = MaterialTheme.customColors().onBackground87
        )
        TeamixTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            singleLine = true
        )
    }
}