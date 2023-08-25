package com.chocolate.presentation.screens.search

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.NoInternetLottie
import com.chocolate.presentation.composable.NotFoundResultLottie
import com.chocolate.presentation.composable.SearchLottie
import com.chocolate.presentation.composable.TabScreen
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.composable.TeamixTextField
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.CollectUiEffect
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.search.SearchEffect
import com.chocolate.viewmodel.search.SearchInteraction
import com.chocolate.viewmodel.search.SearchUiState
import com.chocolate.viewmodel.search.SearchViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val navController = LocalNavController.current
    CollectUiEffect(viewModel = viewModel) { effect ->
        when (effect) {
            is SearchEffect.NavigateToChannel -> {}
            is SearchEffect.NavigateToMember -> {}
        }
    }
    SearchContent(state, viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchContent(state: SearchUiState, searchInteraction: SearchInteraction) {
    val colors = MaterialTheme.customColors()
    TeamixScaffold(
        modifier = Modifier.fillMaxSize(),
        isDarkMode = isSystemInDarkTheme(),
        error = state.error,
        onError = {
            NoInternetLottie(
                text = stringResource(id = R.string.no_internet_connection),
                isShow = state.error != null && state.showNoInternetLottie,
                isDarkMode = isSystemInDarkTheme()
            )
        },
        onRetry = {searchInteraction.onClickRetry()},
    ) { paddingValues ->
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
                    modifier = Modifier.padding(Space16),
                    value = state.query,
                    onValueChange = {searchInteraction.onChangeSearchQuery(it)},
                    containerColor = colors.background,
                    hint = stringResource(id = R.string.search),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = null
                        )
                    }
                )
            }
            val tabs = listOf(
                stringResource(id = R.string.channels),
                stringResource(R.string.members)
            )
            TabScreen(
                state = state,
                textTabs = tabs,
                onClickChannelItem = { searchInteraction.onClickChannelItem(it) },
                onClickMemberItem = { searchInteraction.onClickMemberItem(it) },
                onChangeTabIndex = { searchInteraction.onChangeTabIndex(it) },
                onClickRecentSearchItem = { searchInteraction.onClickRecentSearchItem(it) })
            SearchLottie(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                isShow = state.query.isEmpty() && !state.showNoInternetLottie,
                isDarkMode = isSystemInDarkTheme(),
            )
            when (state.currentTabIndex) {
                0 -> ShowNotFoundResultLottie(
                    isShow = state.channelsUiState.isEmpty() &&
                            state.query.isNotEmpty() && !state.isLoading
                )

                1 -> ShowNotFoundResultLottie(
                    isShow = state.membersUiState.isEmpty() &&
                            state.query.isNotEmpty() && !state.isLoading
                )
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