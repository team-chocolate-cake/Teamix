package com.chocolate.presentation.screens.search.compasbles

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.search.SearchUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabScreen(
    onClickChannelItem: (Int) -> Unit,
    onClickMemberItem: (Int) -> Unit,
    state: SearchUiState
) {
    val colors = MaterialTheme.customColors()
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Channels", "Members")
    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            selectedTabIndex = tabIndex,
            containerColor = colors.card,
            contentColor = colors.card,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    color = colors.primary,
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[tabIndex])
                        .padding(horizontal = 40.dp)
                )
            },
            divider = { }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            title,
                            color = if (tabIndex == index) colors.primary else colors.onBackground87,
                            style = MaterialTheme.typography.titleSmall
                        )
                    },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                )
            }
        }

        Text(
            text = "RecentSearch",
            color = colors.onBackground87,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(state.recentSearches) {
                RecentSearchItem(text = it)
            }
        }
        Crossfade(targetState = tabIndex, animationSpec = tween(500), label = "") { targetTab ->
            when (targetTab) {
                0 -> LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.channelsUiState, key = { item ->
                        item.channelId
                    }) { channelState ->
                        ChannelItem(
                            { onClickChannelItem(it) },
                            channelState,
                            modifier = Modifier.animateItemPlacement()
                        )
                    }
                }

                1 -> LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.membersUiState, key = { item ->
                        item.memberId
                    }) { memberState ->
                        MemberItem(
                            { onClickMemberItem(it) },
                            memberState,
                            modifier = Modifier.animateItemPlacement()
                        )
                    }
                }
            }
        }
    }
}