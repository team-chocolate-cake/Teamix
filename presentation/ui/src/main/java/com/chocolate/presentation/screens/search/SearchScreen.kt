package com.chocolate.presentation.screens.search

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chocolate.presentation.composable.SearchBox
import com.chocolate.presentation.screens.search.compasbles.TabScreen
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.search.SearchEffect
import com.chocolate.viewmodel.search.SearchInteraction
import com.chocolate.viewmodel.search.SearchUiState
import com.chocolate.viewmodel.search.SearchViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is SearchEffect.NavigateToChannel -> {}
            }
        }
    }
    SearchContent(state, viewModel
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchContent(state: SearchUiState, searchInteraction: SearchInteraction) {
    val colors = MaterialTheme.customColors()
    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = colors.background) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(colors.card)) {
                SearchBox(
                    containerColor = MaterialTheme.customColors().background,
                    modifier = Modifier.padding(Space16),
                )
            }
            TabScreen(
                { searchInteraction.onClickChannelItem(it) },
                { searchInteraction.onClickMemberItem(it) }, state
            )

        }
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