package com.chocolate.presentation.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space40
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.search.SearchUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabScreen(
    modifier: Modifier = Modifier,
    state: SearchUiState,
    textTabs: List<String>,
    onClickChannelItem: (Int) -> Unit,
    onClickMemberItem: (Int) -> Unit,
    onChangeTabIndex: (Int) -> Unit,
    onClickRecentSearchItem: (String) -> Unit
) {
    val colors = MaterialTheme.customColors()
    Column(modifier = modifier.fillMaxWidth().wrapContentHeight()) {
        TabRow(
            selectedTabIndex = state.currentTabIndex,
            containerColor = colors.card,
            contentColor = colors.card,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    color = colors.primary,
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[state.currentTabIndex])
                        .padding(horizontal = Space40)
                )
            },
            divider = { }
        ) {
            textTabs.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            title,
                            color = if (state.currentTabIndex == index) colors.primary else colors.onBackground87,
                            style = MaterialTheme.typography.titleSmall
                        )
                    },
                    selected = state.currentTabIndex == index,
                    onClick = { onChangeTabIndex(index) },
                )
            }
        }
        AnimatedVisibility(visible = state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Space16),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = colors.primary,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
        Crossfade(
            targetState = state.currentTabIndex, animationSpec = tween(500), label = ""
        ) { targetTab ->
            when (targetTab) {
                0 -> LazyColumn(
                    modifier = Modifier.wrapContentSize(),
                    contentPadding = PaddingValues(Space16),
                    verticalArrangement = Arrangement.spacedBy(Space8)
                ) {

                    item {
                        RecentSearch(recentSearches = state.recentSearches,
                            query = state.query,
                            onClickRecentSearchItem = { onClickRecentSearchItem(it) })
                    }
                    items(state.channelsUiState, key = { item ->
                        item.id
                    }) { channelState ->
                        ChannelSearchItem(
                            modifier = Modifier.animateItemPlacement(),
                            onClickChannelItem = { onClickChannelItem(it) },
                            id = channelState.id,
                            name = channelState.name,
                            numberOfMembers = channelState.numberOfMembers,
                            isPrivate = channelState.isPrivate
                        )
                    }
                }

                1 -> LazyColumn(
                    modifier = Modifier.wrapContentSize(),
                    contentPadding = PaddingValues(Space16),
                    verticalArrangement = Arrangement.spacedBy(Space8)
                ) {
                    item {
                        RecentSearch(recentSearches = state.recentSearches,
                            query = state.query,
                            onClickRecentSearchItem = { onClickRecentSearchItem(it) })
                    }
                    items(state.membersUiState, key = { item ->
                        item.id
                    }) { memberState ->
                        MemberSearchItem(
                            modifier = Modifier.animateItemPlacement(),
                            onClickMemberItem = { onClickMemberItem(it) },
                            id = memberState.id,
                            name = memberState.name,
                            imageUrl = memberState.imageUrl,
                            isOnline = memberState.isOnline
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RecentSearch(
    recentSearches: List<String>, query: String, onClickRecentSearchItem: (String) -> Unit
) {
    val colors = MaterialTheme.customColors()
    AnimatedVisibility(visible = query.isEmpty() && recentSearches.isNotEmpty()) {
        Column {
            Text(
                text = stringResource(R.string.recent_search),
                color = colors.onBackground87,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = Space16, top = Space16)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(Space8),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = Space16)
            ) {
                items(recentSearches) {
                    RecentSearchItem(text = it,
                        painter = painterResource(id = R.drawable.ic_delete),
                        onClickRecentSearchItem = { onClickRecentSearchItem(it) })
                }
            }
        }
    }
}