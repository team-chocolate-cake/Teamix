package com.chocolate.presentation.screens.home

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.CustomColorsPalette
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemChannel(
    state: ChannelUIState,
    colors: CustomColorsPalette,
    onLongClickChannel: () -> Unit
) {
    val haptics = LocalHapticFeedback.current
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(12.dp))
            .background(color = colors.onPrimary)
            .padding(16.dp)
            .combinedClickable(
                onLongClick = {
                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                    onLongClickChannel()
                    Toast
                        .makeText(context, "Long Click", Toast.LENGTH_SHORT)
                        .show()
                },
                onClick = { })
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val iconsChannel =
                if (state.isPrivateChannel) R.drawable.ic_lock else R.drawable.ic_hashtag
            Icon(
                painter = painterResource(id = iconsChannel),
                contentDescription = null,
                tint = colors.primary,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = state.name,
                style = MaterialTheme.typography.labelLarge,
                color = colors.onBackground87
            )
            Spacer(modifier = Modifier.weight(1f))
            val icons = if (state.isExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
            Icon(
                painter = painterResource(id = icons),
                contentDescription = null,
                tint = colors.onBackground60,
                modifier = Modifier.clickable {

                }
            )
//            LazyColumn {
//                items(items = state.topics) { topicUIState ->
//                    ItemTopic(topicUIState, colors)
//                }
//            }
        }
    }
}

@Composable
fun ItemTopic(topicUIState: TopicsUIState, colors: CustomColorsPalette) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = topicUIState.name)
        Text(text = topicUIState.notificationNumber.toString())
    }
}

@Composable
fun ItemChips(chipsUIState: ChipsUIState, colors: CustomColorsPalette) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(12.dp))
            .background(colors.card)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Card(
                modifier = Modifier.wrapContentSize(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(colors.primary)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxSize(),
                    text = chipsUIState.notificationNumber.toString(),
                    style = MaterialTheme.typography.labelMedium,
                    color = colors.onPrimary,
                    textAlign = TextAlign.End
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_mention),
                contentDescription = "icons",
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                tint = colors.onBackground60,

            )
            Text(
                text = chipsUIState.title,
                color = colors.onBackground60,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 24.dp),
                textAlign = TextAlign.Center
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(state: HomeUIState, colors: CustomColorsPalette) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = state.imageUrl),
                    contentDescription = "image stream",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .padding(end = 12.dp)
                )
                Text(
                    text = state.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = colors.onPrimary
                )
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(colors.primary),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageChannelBottomSheet(colors: CustomColorsPalette, onDismiss: () -> Unit) {
    val modelBottomSheetScaffoldState = rememberModalBottomSheetState()
    var openDialog by remember { mutableStateOf(false) }
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modelBottomSheetScaffoldState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        contentColor = colors.card
    ) {
        if (openDialog) {
            ManageChannelAlertDialog(
                title = "leave",
                subTitle = "a",
                colors,
                onDismiss = { openDialog = false })
        }
        Divider(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            thickness = 4.dp,
            color = colors.onBackground38
        )
        ItemManageChannelBottomSheet(
            colors,
            title = "Mute Channel",
            painter = painterResource(id = R.drawable.ic_mute_channel)
        ) { openDialog = true }
        ItemManageChannelBottomSheet(
            colors,
            title = "Star Channel",
            painter = painterResource(id = R.drawable.ic_star)
        ) { openDialog = true }
        ItemManageChannelBottomSheet(
            colors,
            title = "Copy Link",
            painter = painterResource(id = R.drawable.ic_copy)
        ) { }
        ItemManageChannelBottomSheet(
            colors,
            title = "Copy Name",
            painter = painterResource(id = R.drawable.ic_copy)
        ) { }
        ItemManageChannelBottomSheet(
            colors,
            title = "Leave Channel",
            painter = painterResource(id = R.drawable.ic_leave),
            itemColor = colors.red60
        ) { openDialog = true }
    }
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .wrapContentHeight()
//        ) {
//
//    }
}

@Composable
fun ItemManageChannelBottomSheet(
    colors: CustomColorsPalette,
    title: String,
    painter: Painter,
    modifier: Modifier = Modifier,
    itemColor: Color = colors.onBackground60,
    onClickItemManageChannelBottomSheet: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClickItemManageChannelBottomSheet() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painter,
            contentDescription = title,
            tint = itemColor,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = itemColor
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageChannelAlertDialog(
    title: String,
    subTitle: String,
    colors: CustomColorsPalette,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            colors = CardDefaults.cardColors(colors.card),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 24.dp, bottom = 16.dp),
                text = "Do you want to $title channel",
                style = MaterialTheme.typography.titleMedium,
                color = colors.onBackground87,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp),
                text = subTitle,
                style = MaterialTheme.typography.bodySmall,
                color = colors.onBackground60,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 34.dp)
                    .padding(end = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp, alignment = Alignment.End)
            ) {
                Text(
                    text = "Dismiss",
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.onBackground87,
                    modifier = Modifier.clickable { onDismiss() })
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.red60,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable { onDismiss() })
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
