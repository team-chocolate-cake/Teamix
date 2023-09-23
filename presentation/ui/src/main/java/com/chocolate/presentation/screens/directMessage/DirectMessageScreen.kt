package com.chocolate.presentation.screens.directMessage

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.DirectMessageChat
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.composable.TeamixTextField
import com.chocolate.presentation.screens.directMessageChat.navigateToDmChat
import com.chocolate.presentation.screens.directMessageChooseMember.navigateToDMChooseMember
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.directMessage.DirectMessageInteractions
import com.chocolate.viewmodel.directMessage.DirectMessageUiEffect
import com.chocolate.viewmodel.directMessage.DirectMessageUiState
import com.chocolate.viewmodel.directMessage.DirectMessageViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DirectMessageScreen(dmViewModel: DirectMessageViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    val state by dmViewModel.state.collectAsState()
    LaunchedEffect(key1 = dmViewModel.effect) {
        dmViewModel.effect.collectLatest {
            when (it) {
                DirectMessageUiEffect.NavigateToChooseMember -> navController.navigateToDMChooseMember()
                is DirectMessageUiEffect.NavigateToChat -> {
                    navController.navigateToDmChat(
                        groupId = it.groupId,
                        memberName = it.name,
                    )
                }
            }
        }
    }
    DirectMessageContent(state, dmViewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DirectMessageContent(state: DirectMessageUiState, interactions: DirectMessageInteractions) {
    val colors = MaterialTheme.customColors()
    val systemUiController = rememberSystemUiController()
    val searchQuery by state.searchInput.collectAsState()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.customColors().primary,
        darkIcons = false
    )
    TeamixScaffold(
        isDarkMode = isSystemInDarkTheme(),
        floatingActionButton = {
            FloatingActionButton(
                containerColor = colors.primary,
                onClick = interactions::onClickNewChat
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.chat),
                    contentDescription = "",
                    tint = colors.onPrimary
                )
            }
        }
    ) {
        Column() {
            TeamixTextField(
                modifier = Modifier.padding(SpacingXLarge),
                value = searchQuery,
                singleLine = true,
                onValueChange = { interactions.onChangeSearchQuery(it) },
                containerColor = colors.card,
                hint = stringResource(R.string.search_for_chats),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty())
                        Icon(
                            painter = painterResource(id = R.drawable.ic_delete),
                            contentDescription = null,
                            tint = MaterialTheme.customColors().onBackground87,
                            modifier = Modifier.clickable { interactions.onClickDeleteQuery() }
                        )
                }
            )
            LazyColumn(
                modifier = Modifier.padding(horizontal = SpacingXLarge),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.chats.size) {
                    DirectMessageChat(
                        state = state.chats[it],
                        modifier = Modifier.clickable {
                            interactions.onClickChat(id = state.chats[it].id , name =state.chats[it].name )
                    })
                }
            }
        }
    }
}
