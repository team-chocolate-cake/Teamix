package com.chocolate.presentation.screens.search

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.NoInternetLottie
import com.chocolate.presentation.composable.NotFoundResultLottie
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.composable.TeamixTextField
import com.chocolate.presentation.screens.channel.navigateToChannel
import com.chocolate.presentation.screens.search.composable.ChannelSearchItem
import com.chocolate.presentation.screens.search.composable.SearchLottie
import com.chocolate.presentation.theme.LightCard
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.CollectUiEffect
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.search.SearchEffect
import com.chocolate.viewmodel.search.SearchInteraction
import com.chocolate.viewmodel.search.SearchUiState
import com.chocolate.viewmodel.search.SearchViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val navController = LocalNavController.current
    CollectUiEffect(viewModel.effect) { effect ->
        when (effect) {
            is SearchEffect.NavigateToChannel -> navController.navigateToChannel(
                effect.id,
                effect.name
            )
        }
    }
    SearchContent(state, viewModel)
}

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun SearchContent(state: SearchUiState, searchInteraction: SearchInteraction) {
    val colors = MaterialTheme.customColors()
    val query = state.query.collectAsState()
    val systemUiController = rememberSystemUiController()
    val isDarkIcons = MaterialTheme.customColors().card == LightCard

    TeamixScaffold(
        modifier = Modifier.fillMaxSize(),
        error = state.error,
        onError = {
            NoInternetLottie(
                text = stringResource(id = R.string.no_internet_connection),
                isShow = state.error != null && state.showNoInternetLottie,
                isDarkMode = isSystemInDarkTheme()
            )
        },
        onRetry = { searchInteraction.onClickRetry() },
    ) { paddingValues ->
        systemUiController.setStatusBarColor(MaterialTheme.customColors().card, darkIcons = isDarkIcons)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(colors.card)
            ) {
                TeamixTextField(
                    modifier = Modifier.padding(SpacingXLarge),
                    value = query.value,
                    singleLine = true,
                    onValueChange = { searchInteraction.onChangeSearchQuery(it) },
                    containerColor = colors.background,
                    hint = stringResource(R.string.search_for_channels),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        AnimatedVisibility(visible = state.query.value.isNotBlank()) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_delete),
                                contentDescription = null,
                                tint = MaterialTheme.customColors().onBackground87,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .clickable { searchInteraction.onClickDeleteQuery() }
                            )
                        }
                    }
                )
            }
            AnimatedVisibility(visible = state.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = SpacingXLarge),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = colors.primary,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
            LazyColumn(
                modifier = Modifier.wrapContentSize(),
                contentPadding = PaddingValues(SpacingXLarge),
                verticalArrangement = Arrangement.spacedBy(SpacingXMedium)
            ) {
                items(state.channelsUiState, key = { item ->
                    item.id
                }) { channelState ->
                    ChannelSearchItem(
                        modifier = Modifier.animateItemPlacement(),
                        onClickChannelItem = { id, name ->
                            searchInteraction.onClickChannelItem(id, name)
                        },
                        id = channelState.id,
                        name = channelState.name,
                        numberOfMembers = channelState.numberOfMembers,
                        isPrivate = channelState.isPrivate
                    )
                }
            }
            SearchLottie(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                isShow = query.value.isEmpty() && !state.showNoInternetLottie,
                isDarkMode = isSystemInDarkTheme(),
            )
            ShowNotFoundResultLottie(
                isShow = state.isChannelsEmpty &&
                        query.value.isNotEmpty() && !state.isLoading
            )
        }
    }
}

@Composable
private fun ShowNotFoundResultLottie(isShow: Boolean) {
    Column {
        NotFoundResultLottie(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            isShow = isShow,
            isDarkMode = isSystemInDarkTheme(),
        )
    }
}

@Composable
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showSystemUi = true
)
fun SearchPreview() {
    val viewModel: SearchViewModel = hiltViewModel()
    SearchContent(state = SearchUiState(), searchInteraction = viewModel)
}