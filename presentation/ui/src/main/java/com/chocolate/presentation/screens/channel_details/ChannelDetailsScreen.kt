package com.chocolate.presentation.screens.channel_details

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.screens.channel_details.composable.ChannelAction
import com.chocolate.presentation.composable.Member
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXXLarge
import com.chocolate.presentation.theme.SpacingExtraHuge
import com.chocolate.presentation.theme.SpacingMedium
import com.chocolate.presentation.theme.SpacingUltraGigantic
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors

@Composable
fun ChannelDetailsScreen() {
    ChannelDetailsContent(
        channelDetailsUiState = ChannelDetailsUiState(),
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
                    stringResource(R.string.do_you_want_to_leave_channel),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.customColors().onBackground87
                )
            },
            text = {
                Text(
                    stringResource(R.string.leave_discription),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.customColors().onBackground60
                )
            },
            confirmButton = {
                TextButton(
                    onClick = onLeaveChannel
                ) {
                    Text(stringResource(R.string.leave), color = MaterialTheme.customColors().red60)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(
                        stringResource(R.string.dismiss_btn),
                        color = MaterialTheme.customColors().onBackground87
                    )
                }
            },
        )
    }

    TeamixScaffold(
        isDarkMode = isSystemInDarkTheme()
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = SpacingExtraHuge),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(padding)
            ) {
                LazyVerticalGrid(
                    modifier = Modifier.padding(SpacingXLarge),
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(SpacingXMedium)
                ) {
                    item {
                        ChannelAction(
                            text = stringResource(R.string.add_user),
                            icon = painterResource(R.drawable.add_user),
                            onItemClicked = onAddUser
                        )
                    }
                    item {
                        ChannelAction(
                            text = stringResource(R.string.meet_action),
                            icon = painterResource(id = R.drawable.meeting_call),
                            onItemClicked = onMeetingCall
                        )
                    }
                    item {
                        ChannelAction(
                            text = stringResource(R.string.search_action),
                            icon = painterResource(R.drawable.search),
                            onItemClicked = onSearch
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = SpacingXLarge, end = SpacingXLarge, bottom = SpacingXMedium),
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
                        text = stringResource(
                            R.string.see_all
                        ),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.customColors().primary
                    )
                }
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(SpacingXLarge),
                    contentPadding = PaddingValues(horizontal = SpacingXLarge, vertical = SpacingXMedium)
                ) {
                    items(channelDetailsUiState.memberUiState.size) {
                        Member(channelDetailsUiState.memberUiState[it])
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(width = 1.dp, color = MaterialTheme.customColors().border)
                        .padding(SpacingXLarge)
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
                            .padding(SpacingXLarge)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                modifier = Modifier.size(SpacingXXLarge),
                                painter = painterResource(id = R.drawable.pin_message),
                                contentDescription = "",
                                tint = MaterialTheme.customColors().onBackground60
                            )
                            Column(modifier = Modifier.padding(start = SpacingXMedium)) {
                                Text(
                                    modifier = Modifier.padding(top = SpacingMedium),
                                    text = stringResource(
                                        R.string.pinned_message
                                    ),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.customColors().onBackground87
                                )
                                Text(
                                    modifier = Modifier,
                                    text = channelDetailsUiState.pinCount.toString() + " " + stringResource(
                                        R.string.pinned_message
                                    ),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.customColors().onBackground60
                                )
                            }
                        }
                        Icon(
                            modifier = Modifier
                                .size(SpacingXXLarge)
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
                        .padding(SpacingXLarge),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                    ) {
                        Text(
                            modifier = Modifier.padding(top = SpacingMedium),
                            text = stringResource(R.string.mute_channel),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.customColors().onBackground87
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(0.6f),
                            text = stringResource(R.string.muted_channel_description),
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
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(vertical = SpacingXMedium)
                        .fillMaxWidth()
                        .height(SpacingUltraGigantic)
                        .padding(horizontal = SpacingExtraHuge)
                        .border(
                            1.dp,
                            color = MaterialTheme.customColors().red60,
                            shape = RoundedCornerShape(SpacingXLarge)
                        )
                        .clip(RoundedCornerShape(SpacingXLarge))
                        .clickable {
                            showDialog = true
                        }) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.logout),
                            contentDescription = "",
                            tint = MaterialTheme.customColors().red
                        )
                        Spacer(modifier = Modifier.width(SpacingXMedium))
                        Text(
                            text = stringResource(R.string.leave_channel),
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

