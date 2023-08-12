package com.chocolate.presentation.screens.home

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chocolate.presentation.R
import com.chocolate.presentation.screens.home.compose.BadgeHome
import com.chocolate.presentation.screens.home.compose.ChannelItem
import com.chocolate.presentation.screens.home.compose.ManageChannelBottomSheet
import com.chocolate.presentation.screens.home.compose.TeamixTopAppBar
import com.chocolate.presentation.screens.organiztion.navigateToOrganizationName
import com.chocolate.presentation.theme.CustomColorsPalette
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space24
import com.chocolate.presentation.theme.Space4
import com.chocolate.presentation.theme.Space64
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.home.CardItemContent
import com.chocolate.viewmodel.home.HomeUiState
import com.chocolate.viewmodel.home.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val state by homeViewModel.state.collectAsState()

    if(state.isLogged){
        HomeContent(
            state = state,
            navigationToMention = {},
            navigationToDrafts = {},
            navigationToStarred = {},
            navigationToSavedLater = {},
            navigateToChannel = {},
        )
    }else{
        LaunchedEffect(Unit){ navController.navigateToOrganizationName() }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContent(
    state: HomeUiState,
    navigationToMention: () -> Unit,
    navigationToDrafts: () -> Unit,
    navigationToStarred: () -> Unit,
    navigationToSavedLater: () -> Unit,
    navigateToChannel: (Int) -> Unit
) {
    val colors = MaterialTheme.customColors()
    var isShowSheet by remember { mutableStateOf(false) }


    if (isShowSheet) {
        ManageChannelBottomSheet(onDismissBottomSheet = { isShowSheet = false }, colors = colors)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TeamixTopAppBar(
                imageUrl = state.imageUrl,
                title = state.titleOrganization,
                colors = colors
            )
        },
        containerColor = colors.background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = Space64),
            contentPadding = PaddingValues(vertical = Space16),
            verticalArrangement = Arrangement.spacedBy(Space8),
        ) {
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    contentPadding = PaddingValues(horizontal = Space16)
                ) {
                    val cardList = mutableListOf<CardItemContent>().apply {
                        add(
                            CardItemContent(
                                state.badgeCountsUiState.drafts,
                                "Drafts",
                                R.drawable.ic_drafts
                            )
                        )
                        add(
                            CardItemContent(
                                state.badgeCountsUiState.saved,
                                "Saved Later",
                                R.drawable.ic_saved_later
                            )
                        )
                    }
                    itemsIndexed(cardList) { index, item ->
                        CardItem(
                            badge = item.badgeCount,
                            painter = painterResource(item.icon),
                            title = item.title,
                            clickIndex = index,
                            colors = colors,
                            onClickItemCard = {
                                when (it) {
                                    0 -> {navigationToMention()}
                                    1 -> {navigationToDrafts()}
                                    2 -> {navigationToStarred()}
                                    3 -> {navigationToSavedLater()}
                                }
                            },
                            modifier = Modifier.padding(end = Space8)
                        )
                    }
                }
            }
            item {
                Text(
                    text = stringResource(R.string.channels),
                    style = MaterialTheme.typography.bodyLarge,
                    color = colors.onBackground87,
                    modifier = Modifier
                        .padding(top = Space8)
                        .padding(horizontal = Space16)
                )
            }
            items(items = state.channelsUIState, key = { currentChannel ->
                currentChannel.name
            }) { channelUIState ->
                ChannelItem(
                    channelUIState,
                    colors,
                    onLongClickChannel = { isShowSheet = true },
                    onClickItemChannel = { navigateToChannel(channelUIState.channelId) },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .animateItemPlacement()
                )
            }
        }
    }
}

@Composable
private fun CardItem(
    badge: Int,
    painter: Painter,
    title: String,
    clickIndex: Int,
    colors: CustomColorsPalette,
    onClickItemCard: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .height(96.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(colors.card)
            .clickable { onClickItemCard(clickIndex) },
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            BadgeHome(
                number = badge,
                textColor = colors.onPrimary,
                cardColor = colors.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.End)
                    .padding(end = Space4, top = Space4)
            )
            Icon(
                painter = painter,
                contentDescription = "icons",
                modifier = Modifier
                    .wrapContentSize()
                    .padding(bottom = Space8)
                    .align(Alignment.CenterHorizontally),
                tint = colors.onBackground60,
            )
            Text(
                text = title,
                color = colors.onBackground60,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = Space24)
                    .padding(horizontal = 26.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
fun HomePreview() {
    TeamixTheme {
        HomeContent(
            state = HomeUiState(),
            navigationToMention = { /*TODO*/ },
            navigationToDrafts = { /*TODO*/ },
            navigationToStarred = { /*TODO*/ },
            navigationToSavedLater = { /*TODO*/ },
            navigateToChannel = {}
        )
    }
}