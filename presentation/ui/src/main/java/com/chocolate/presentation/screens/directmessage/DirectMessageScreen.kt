package com.chocolate.presentation.screens.directmessage

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.DirectMessageChat
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.composable.TeamixTextField
import com.chocolate.presentation.screens.directmessagechat.navigateToDirectMessageChat
import com.chocolate.presentation.screens.directmessagechoosemember.navigateToDirectMessageChooseMember
import com.chocolate.presentation.theme.Height56
import com.chocolate.presentation.theme.LightCard
import com.chocolate.presentation.theme.Radius8
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXXMedium
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.CollectUiEffect
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.presentation.util.hideKeyboard
import com.chocolate.viewmodel.directmessage.DirectMessageInteractions
import com.chocolate.viewmodel.directmessage.DirectMessageUiEffect
import com.chocolate.viewmodel.directmessage.DirectMessageUiState
import com.chocolate.viewmodel.directmessage.DirectMessageViewModel

@Composable
fun DirectMessageScreen(dmViewModel: DirectMessageViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    val state by dmViewModel.state.collectAsState()
    CollectUiEffect(dmViewModel.effect) {
        when (it) {
            DirectMessageUiEffect.NavigateToChooseMember -> navController.navigateToDirectMessageChooseMember()
            is DirectMessageUiEffect.NavigateToChat -> {
                navController.navigateToDirectMessageChat(
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
    val searchQuery by state.searchInput.collectAsState()
    val context = LocalContext.current
    val rootView = LocalView.current
    TeamixScaffold(
        modifier = Modifier.fillMaxSize(),
        containerColorAppBar = MaterialTheme.customColors().primary,
        title = stringResource(id = R.string.direct_messages_text),
        titleColor = LightCard,
        hasAppBar = true,
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier,
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
        onLoading = {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = colors.primary
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            TeamixTextField(
                modifier = Modifier
                    .padding(SpacingXLarge)
                    .fillMaxWidth()
                    .height(Height56),
                value = searchQuery,
                keyboardActions = KeyboardActions(onDone = { hideKeyboard(context, rootView) }),
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
                verticalArrangement = Arrangement.spacedBy(Radius8)
            ) {
                items(state.chats.size) {
                    DirectMessageChat(
                        state = state.chats[it],
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(SpacingXXMedium))
                            .clickable {
                                interactions.onClickChat(
                                    id = state.chats[it].id,
                                    name = state.chats[it].name
                                )
                            }
                    )
                }
            }
        }
    }
}
