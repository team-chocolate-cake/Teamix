package com.chocolate.presentation.screens.direct_message

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.screens.search.composable.MemberSearchItem
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.directMessage.DirectMessageUiState
import com.chocolate.viewmodel.directMessage.DirectMessageViewModel

@Composable
fun DirectMessageScreen(
    viewModel: DirectMessageViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val state by viewModel.state.collectAsState()
    DirectMessageContent(state,{})
}

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DirectMessageContent(state: DirectMessageUiState,onClickMemberItem: (Int) -> Unit) {
    val context = LocalContext.current
    TeamixScaffold(isDarkMode = isSystemInDarkTheme()) {paddingValue->
        LazyColumn(
            modifier = Modifier
                .wrapContentSize()
                .padding(paddingValue),
            contentPadding = PaddingValues(SpacingXLarge),
            verticalArrangement = Arrangement.spacedBy(SpacingXMedium)
        ) {
            item {

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
