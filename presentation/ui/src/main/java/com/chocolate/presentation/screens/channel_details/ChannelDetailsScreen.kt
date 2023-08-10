package com.chocolate.presentation.screens.channel_details

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.screens.channel.composables.CustomAppBar
import com.chocolate.presentation.screens.channel_details.compasbles.ChannelAction
import com.chocolate.presentation.screens.channel_details.compasbles.Member
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space24
import com.chocolate.presentation.theme.Space32
import com.chocolate.presentation.theme.Space4
import com.chocolate.presentation.theme.Space56
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors

@Composable
fun ChannelDetailsScreen() {
    ChannelDetailsContent(
        channelDetailsUiState = ChannelDetailsUiState(),
        navigationBack = {},
        onAddUser = {},
        onMeetingCall = {},
        onSearch = {},
        onMuteChannel = {},
        onLeaveChannel = {},
        onSeeAll = {},
        onNavigateToPin = {},
    )
}

@Composable
fun ChannelDetailsContent(
    channelDetailsUiState: ChannelDetailsUiState,
    navigationBack: () -> Unit,
    onAddUser: () -> Unit,
    onMeetingCall: () -> Unit,
    onSearch: () -> Unit,
    onLeaveChannel: () -> Unit,
    onSeeAll: () -> Unit,
    onNavigateToPin: () -> Unit,
    onMuteChannel: (Boolean) -> Unit,
) {

    var showDialog by remember {
        mutableStateOf(false)
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    "Do you want to leave channel",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.customColors().onBackground87
                )
            },
            text = {
                Text(
                    "This action cannot be undoneBy leaving the channel, you will no longer receive updates or messages from this channel",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.customColors().onBackground60
                )
            },
            confirmButton = {
                TextButton(
                    onClick = onLeaveChannel
                ) {
                    Text("Leave", color = MaterialTheme.customColors().red60)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Dismiss", color = MaterialTheme.customColors().onBackground87)
                }
            },
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.customColors().background,
        topBar = {
            //todo this app bar must be changed to be one composable for all screens
            CustomAppBar(
                title = channelDetailsUiState.channelName,
                navigationBack = navigationBack,
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = Space32),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(padding)
            ) {
                LazyVerticalGrid(
                    modifier = Modifier.padding(Space16),
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(Space8)
                ) {
                    item {
                        ChannelAction(
                            text = "Add",
                            icon = R.drawable.add_user,
                            onItemClicked = onAddUser
                        )
                    }
                    item {
                        ChannelAction(
                            text = "Meet",
                            icon = R.drawable.meeting_call,
                            onItemClicked = onMeetingCall
                        )
                    }
                    item {
                        ChannelAction(
                            text = "Search",
                            icon = R.drawable.search,
                            onItemClicked = onSearch
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = Space16, end = Space16, bottom = Space8),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Members (${channelDetailsUiState.memberUiState.size})",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.customColors().onBackground87
                    )
                    Text(
                        modifier = Modifier.clickable {
                            onSeeAll()
                        },
                        text = "See All",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.customColors().primary
                    )
                }
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(Space16),
                    contentPadding = PaddingValues(horizontal = Space16, vertical = Space8)
                ) {
                    items(channelDetailsUiState.memberUiState.size) {
                        Member(channelDetailsUiState.memberUiState[it])
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(width = 1.dp, color = MaterialTheme.customColors().border)
                        .padding(Space16)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable {
                            onNavigateToPin()
                        },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.customColors().card)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(Space16)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                modifier = Modifier.size(Space24),
                                painter = painterResource(id = R.drawable.pin_message),
                                contentDescription = "",
                                tint = MaterialTheme.customColors().onBackground60
                            )
                            Column(modifier = Modifier.padding(start = Space8)) {
                                Text(
                                    modifier = Modifier.padding(top = Space4),
                                    text = "Pinned Messages",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.customColors().onBackground87
                                )
                                Text(
                                    modifier = Modifier,
                                    text = "${channelDetailsUiState.pinCount} Pinned Message",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.customColors().onBackground60
                                )
                            }
                        }
                        Icon(
                            modifier = Modifier
                                .size(Space24)
                                .rotate(180f),
                            painter = painterResource(id = R.drawable.alt_arrow_left),
                            contentDescription = "",
                            tint = MaterialTheme.customColors().onBackground60
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Space16),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                    ) {
                        Text(
                            modifier = Modifier.padding(top = Space4),
                            text = "Mute Channel",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.customColors().onBackground87
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(0.6f),
                            text = "muted channel will always appear read and you won't to receive any notifications from them",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.customColors().onBackground60
                        )
                    }
                    Switch(
                        colors = SwitchDefaults.colors(checkedTrackColor = MaterialTheme.customColors().primary),
                        checked = channelDetailsUiState.channelMuted,
                        onCheckedChange = onMuteChannel
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(vertical = Space8)
                        .fillMaxWidth()
                        .height(Space56)
                        .padding(horizontal = Space32)
                        .border(
                            1.dp,
                            color = MaterialTheme.customColors().red60,
                            shape = RoundedCornerShape(Space16)
                        )
                        .clip(RoundedCornerShape(Space16))
                        .clickable {
                            showDialog = true
                        }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.logout),
                            contentDescription = "",
                            tint = MaterialTheme.customColors().red
                        )
                        Spacer(modifier = Modifier.width(Space8))
                        Text(
                            text = "Leave Channel",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.customColors().red
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun ChannelDetailsScreenPreview() {
    TeamixTheme {
        ChannelDetailsScreen()
    }
}

