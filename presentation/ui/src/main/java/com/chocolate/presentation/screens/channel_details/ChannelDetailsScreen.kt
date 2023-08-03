package com.chocolate.presentation.screens.channel_details

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.chocolate.presentation.R
import com.chocolate.presentation.screens.channel.composables.CustomAppBar
import com.chocolate.presentation.screens.channel.composables.Topic
import com.chocolate.presentation.screens.channel_details.compasbles.ChannelAction
import com.chocolate.presentation.screens.channel_details.compasbles.Member
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space32
import com.chocolate.presentation.theme.Space4
import com.chocolate.presentation.theme.Space40
import com.chocolate.presentation.theme.Space48
import com.chocolate.presentation.theme.Space56
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors

@Composable
fun ChannelDetailsScreen() {
    ChannelDetailsContent(navigationBack = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChannelDetailsContent(
    navigationBack: () -> Unit,
) {
    val actions: List<ChannelAction> = listOf(
        ChannelAction(icon = R.drawable.add_user, text = "Add", action = {
            //todo add user
        }),
        ChannelAction(icon = R.drawable.meeting_call, text = "Meet", action = {
//todo meeting
        }),
        ChannelAction(icon = R.drawable.search, text = "Search", action = {
//todo search
        }),
    )
    var mutedChannel by remember {
        mutableStateOf(false)
    }
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
                    onClick = {
                        //todo leaved the channel
                    }
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
                title = "Chocolate Team",
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
                    items(3) {
                        ChannelAction(actions[it])
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = Space16, end = Space16, bottom = Space8),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Members",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.customColors().onBackground87
                    )
                    Text(
                        modifier = Modifier.clickable {
                            //todo see all
                        },
                        text = "See All",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.customColors().primary
                    )
                }
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(Space16),
                    contentPadding = PaddingValues(horizontal = Space16)
                ) {
                    items(10) {
                        Member()
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Space16)
                        .clickable {
                            //todo go to pin messages
                        }
                ) {
                    Text(
                        modifier = Modifier.padding(top = Space4),
                        text = "Bookmarks and Pins (1)",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.customColors().onBackground87
                    )
                    Text(
                        modifier = Modifier,
                        text = "1 Pinned Message",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.customColors().onBackground60
                    )
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
                            style = MaterialTheme.typography.labelMedium,
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
                        checked = mutedChannel,
                        onCheckedChange = { mutedChannel = it }
                    )
                }
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
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

@Composable
@Preview(showSystemUi = true)
fun ChannelDetailsScreenPreview() {
    TeamixTheme {
        ChannelDetailsScreen()
    }
}

