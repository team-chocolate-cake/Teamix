package com.chocolate.presentation.screens.directmessage

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.DirectMessageChat
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.composable.TeamixTextField
import com.chocolate.presentation.screens.directmessagechat.navigateToDmChat
import com.chocolate.presentation.screens.directmessagechoosemember.navigateToDMChooseMember
import com.chocolate.presentation.theme.LightCard
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.CollectUiEffect
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.directmessage.DirectMessageInteractions
import com.chocolate.viewmodel.directmessage.DirectMessageUiEffect
import com.chocolate.viewmodel.directmessage.DirectMessageUiState
import com.chocolate.viewmodel.directmessage.DirectMessageViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun DirectMessageScreen(dmViewModel: DirectMessageViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    val state by dmViewModel.state.collectAsState()
    CollectUiEffect(dmViewModel.effect) {
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
    DirectMessageContent(state, dmViewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DirectMessageContent(state: DirectMessageUiState, interactions: DirectMessageInteractions) {
    val colors = MaterialTheme.customColors()
    val systemUiController = rememberSystemUiController()
    val searchQuery by state.searchInput.collectAsState()
    val isDarkIcons = MaterialTheme.customColors().card == LightCard

    TeamixScaffold(
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
        },
        isLoading = state.isLoading,
        onLoading =  {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    ) {
        systemUiController.setStatusBarColor(
            color = MaterialTheme.customColors().background,
            darkIcons = isDarkIcons
        )
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
                            interactions.onClickChat(
                                id = state.chats[it].id,
                                name = state.chats[it].name
                            )
                        })
                }
            }
        }
    }
}
