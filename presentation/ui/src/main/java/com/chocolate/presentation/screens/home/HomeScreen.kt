package com.chocolate.presentation.screens.home

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.screens.home.compose.HomeAppBar
import com.chocolate.presentation.screens.home.compose.ItemChannel
import com.chocolate.presentation.screens.home.compose.ItemChips
import com.chocolate.presentation.screens.home.compose.ManageChannelBottomSheet
import com.chocolate.presentation.theme.customColors

@Composable
fun HomeScreen() {
    HomeContent(
        state = HomeUIState(
            "The Chance Community",
            imageUrl = "https://images.pexels.com/photos/1133957/pexels-photo-1133957.jpeg",
            chipsUIState = listOf(
                ChipsUIState("Mentions", 2, R.drawable.ic_mention),
                ChipsUIState("Drafts", 2, R.drawable.ic_drafts),
                ChipsUIState("Starred",10, R.drawable.ic_star),
                ChipsUIState("Saved Later",35, R.drawable.ic_saved_later),
            ),
            channelsUIState = listOf(
                ChannelUIState(
                    name = "The Chance Plus",
                    topics = listOf(
                        TopicsUIState("Chat", 2),
                        TopicsUIState("Chat", 2),
                        TopicsUIState("Chat", 2),
                    ),
                    isPrivateChannel = true
                ),
                ChannelUIState(
                    name = "The Chance Plus",
                    topics = listOf(
                        TopicsUIState("Chat", 2),
                        TopicsUIState("Chat", 2),
                        TopicsUIState("Chat", 2),
                    )
                ),
                ChannelUIState(
                    name = "The Chance Plus",
                    topics = listOf(
                        TopicsUIState("Chat", 2),
                        TopicsUIState("Chat", 2),
                        TopicsUIState("Chat", 2),
                    ),
                    isPrivateChannel = true

                ),
                ChannelUIState(
                    name = "The Chance Plus",
                    topics = listOf(
                        TopicsUIState("Chat", 2),
                        TopicsUIState("Chat", 2),
                        TopicsUIState("Chat", 2),
                    )
                ),
                ChannelUIState(
                    name = "The Chance Plus",
                    topics = listOf(
                        TopicsUIState("Chat", 2),
                        TopicsUIState("Chat", 2),
                        TopicsUIState("Chat", 2),
                    ),
                    isPrivateChannel = true
                ),
                ChannelUIState(
                    name = "The Chance Plus",
                    topics = listOf(
                        TopicsUIState("Chat", 2),
                        TopicsUIState("Chat", 2),
                        TopicsUIState("Chat", 2),
                    ),
                    isExpanded = true
                ),
                ChannelUIState(
                    name = "The Chance Plus",
                    topics = listOf(
                        TopicsUIState("Chat", 2),
                        TopicsUIState("Chat", 2),
                        TopicsUIState("Chat", 2),
                    )
                ),
                ChannelUIState(
                    name = "The Chance Plus",
                    topics = listOf(
                        TopicsUIState("Chat", 2),
                        TopicsUIState("Chat", 2),
                        TopicsUIState("Chat", 2),
                    )
                ),
                ChannelUIState(
                    name = "The Chance Plus",
                    topics = listOf(
                        TopicsUIState("Chat", 2),
                        TopicsUIState("Chat", 2),
                        TopicsUIState("Chat", 2),
                    )
                ),
                ChannelUIState(
                    name = "The Chance Plus",
                    topics = listOf(
                        TopicsUIState("Chat", 2),
                        TopicsUIState("Chat", 2),
                        TopicsUIState("Chat", 2),
                    )
                ),
            )

        )
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContent(state: HomeUIState) {
    val colors = MaterialTheme.customColors()
    var isShowSheet by remember { mutableStateOf(false) }


    if (isShowSheet) {
        ManageChannelBottomSheet(colors = colors) {
            isShowSheet = false
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { HomeAppBar(state, colors) },
        contentColor = colors.background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 64.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(items = state.chipsUIState) { chipsUIState ->
                        ItemChips(chipsUIState, colors)
                    }
                }
            }
            item {
                Text(
                    text = "Channels",
                    style = MaterialTheme.typography.bodyLarge,
                    color = colors.onBackground87,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .padding(horizontal = 16.dp)
                )
            }
            items(items = state.channelsUIState) { channelUIState ->
                ItemChannel(channelUIState, colors) {
                    isShowSheet = true
                }
            }
        }
    }
}

@Composable
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
fun HomePreview() {
    HomeContent(
        state = HomeUIState(
            "The Chance Community",
            imageUrl = "https://images.pexels.com/photos/1133957/pexels-photo-1133957.jpeg",
            chipsUIState = listOf(
                ChipsUIState("Mentions", 2, R.drawable.ic_mention),
                ChipsUIState("Drafts", 2, R.drawable.ic_drafts),
                ChipsUIState("Starred", R.drawable.ic_star),
                ChipsUIState("Saved Later", R.drawable.ic_saved_later),
            )
        )
    )
}
