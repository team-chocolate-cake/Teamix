package com.jetpackcompose.tablayout

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.R
import com.chocolate.presentation.saveLater.SaveLaterScreenContent
import com.chocolate.presentation.saveLater.tap.SaveLaterViewModel
import com.chocolate.presentation.saveLater.tap.TabItem
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TabLayoutScreen(
    viewModel: SaveLaterViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val state = viewModel.state.collectAsState().value

    val tabs = listOf(
        TabItem(
            title = "In Progress",
            screen = { SaveLaterScreenContent(state.inProgressSavedItem) }
        ),
        TabItem(
            title = "Archived",
            screen = { SaveLaterScreenContent(state.archivedSavedItem) }
        ),
        TabItem(
            title = "Completed",
            screen = { SaveLaterScreenContent(state.completedSavedItem) }
        )
    )
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                            contentDescription = "Back"
                        )
                    }
                },
                title = {
                    Text(
                        text = "Later",
                        color = Color.Black
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            TabRow(
                selectedTabIndex = pagerState.currentPage,
            ) {
                tabs.forEachIndexed { index, item ->
                    Tab(
                        selected = index == pagerState.currentPage,
                        text = { Text(text = item.title) },
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    )
                }
            }
            HorizontalPager(
                pageCount = tabs.size,
                state = pagerState
            ) {
                tabs[pagerState.currentPage].screen()
            }
        }
    }

}