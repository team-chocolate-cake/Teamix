package com.chocolate.presentation.screens.create_channel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.chocolate.presentation.composable.TeamixTextField
import com.chocolate.presentation.composable.ToggleButton
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.createChannel.ChannelStatus
import com.chocolate.viewmodel.createChannel.CreateChannelInteraction
import com.chocolate.viewmodel.createChannel.CreateChannelUiState
import com.chocolate.viewmodel.createChannel.CreateChannelViewModel

@Composable
fun CreateChannelScreen(
    createChannelViewModel: CreateChannelViewModel = hiltViewModel()

) {
    val state by createChannelViewModel.state.collectAsState()

    CreateChannelContent(
        state = state,
        createChannelInteraction = createChannelViewModel
    )
}

@Composable
private fun CreateChannelContent(
    state: CreateChannelUiState,
    createChannelInteraction: CreateChannelInteraction
) {
    val colors = MaterialTheme.customColors()
    val textStyle = MaterialTheme.typography

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.background)
            .padding(Space16)

    ) {
        Text(
            modifier = Modifier.padding(bottom = Space8),
            text = stringResource(id = R.string.channel_name),
            style = textStyle.labelMedium,
            color = colors.onBackground87,
            textAlign = TextAlign.Start
        )

        TeamixTextField(
            value = state.nameInput,
            onValueChange = { createChannelInteraction.onChannelNameTextChange(it) }
        )

        Text(
            modifier = Modifier.padding(bottom = Space8, top = Space16),
            text = stringResource(id = R.string.channel_description),
            style = textStyle.labelMedium,
            color = colors.onBackground87,
            textAlign = TextAlign.Start
        )

        TeamixTextField(
            value = state.description ?: "",
            singleLine = true,
            maxLines = 3,
            minLines = 3,
            onValueChange = { createChannelInteraction.onChannelDescriptionChange(it) }
        )

        Text(
            modifier = Modifier.padding(bottom = Space8, top = Space16),
            text = stringResource(id = R.string.status),
            style = textStyle.labelMedium,
            color = colors.onBackground87,
            textAlign = TextAlign.Start
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Space8)
        ) {
            ToggleButton(
                modifier = Modifier.weight(1f),
                color = colors.primary,
                isFilled = state.status == ChannelStatus.Private,
                onClick = {createChannelInteraction.onChannelStatusChange(newChannelStatus = ChannelStatus.Private)}
            ){
                Text(
                    modifier = Modifier.padding(bottom = Space8, top = Space16),
                    text = stringResource(id = R.string.private_text),
                    style = textStyle.labelSmall,
                    color = if(state.status == ChannelStatus.Private) colors.white else colors.primary,
                    textAlign = TextAlign.Center
                )
            }

            ToggleButton(
                modifier = Modifier.weight(1f),
                color = colors.primary,
                isFilled = state.status == ChannelStatus.Public,
                onClick = {createChannelInteraction.onChannelStatusChange(newChannelStatus = ChannelStatus.Public)}
            ){
                Text(
                    modifier = Modifier.padding(bottom = Space8, top = Space16),
                    text = stringResource(id = R.string.public_text),
                    style = textStyle.labelSmall,
                    color = if(state.status == ChannelStatus.Public) colors.white else colors.primary,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        ToggleButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.End),
            color = colors.primary,
            isFilled = true,
            onClick = {createChannelInteraction.onCreateChannelClicked()}
        ){
            Text(
                modifier = Modifier.padding(bottom = Space8, top = Space16),
                text = stringResource(id = R.string.create_channel),
                style = textStyle.bodyLarge,
                color =  colors.white,
                textAlign = TextAlign.Center
            )
        }


    }
}