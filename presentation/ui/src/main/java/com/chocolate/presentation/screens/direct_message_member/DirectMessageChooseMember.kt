package com.chocolate.presentation.screens.direct_message_member

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.MemberItem
import com.chocolate.presentation.composable.NoInternetLottie
import com.chocolate.presentation.composable.SelectedMemberItem
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.composable.TeamixTextField
import com.chocolate.presentation.screens.create_channel.composable.ActionSnakeBar
import com.chocolate.presentation.screens.home.navigateToHome
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.chooseMember.ChooseMemberInteraction
import com.chocolate.viewmodel.chooseMember.ChooseMemberUiState
import com.chocolate.viewmodel.chooseMember.ChooseMemberViewModel
import com.chocolate.viewmodel.dm_choose_member.DMChooseMemberInteraction
import com.chocolate.viewmodel.dm_choose_member.DMChooseMemberUiState
import com.chocolate.viewmodel.dm_choose_member.DMDMChooseMemberViewModel

@Composable
fun DirectMessageChooseMemberScreen(
    viewModel: DMDMChooseMemberViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    DirectMessageChooseMemberContent(state = state, viewModel)

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DirectMessageChooseMemberContent(
    state: DMChooseMemberUiState,
    interaction: DMChooseMemberInteraction
) {
    val colors = MaterialTheme.customColors()
    val context = LocalContext.current
    val text =
        if (state.selectedMembersUiState.isEmpty()) stringResource(R.string.skip) else stringResource(
            R.string.ok
        )
    val navController = LocalNavController.current
    TeamixScaffold(
        isDarkMode = isSystemInDarkTheme(),
        containerColorAppBar = colors.card,
        hasAppBar = true,
        hasBackArrow = true,
        actionsAppbar = {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = colors.primary,
                modifier = Modifier
                    .padding(end = SpacingXMedium)
                    .clickable {
                        if (state.selectedMembersUiState.isEmpty()) {
                            navController.navigateToHome()
                        } else {
                            val selectedMemberIds = state.selectedMembersUiState.map { it.userId }
//                            chooseMemberInteraction.addMembersInChannel(
//                                channelName = state.channelName,
//                                usersId = selectedMemberIds
//                            )
                            navController.navigateToHome()
                        }
                    }
            )
        },
        title = stringResource(R.string.choose_members),
        isLoading = state.isLoading,
        onLoading = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator(color = colors.primary) }
        },
        error = state.error,
        onRetry = interaction::onClickRetry,
        onError = {
            NoInternetLottie(
                text = stringResource(id = R.string.no_internet_connection),
                isShow = state.error != null && state.successMessage == null,
                isDarkMode = isSystemInDarkTheme(),
            )
        }
    ) { paddingValues ->
        Box {


            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(vertical = SpacingXLarge),
                verticalArrangement = Arrangement.spacedBy(SpacingXLarge)
            ) {
                item {
                    TeamixTextField(value = state.searchQuery,
                        modifier = Modifier.padding(horizontal = SpacingXLarge),
                        hint = stringResource(id = R.string.search),
                        onValueChange = interaction::onChangeSearchQuery ,
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.search),
                                contentDescription = null
                            )
                        })
                }
                item {
                    AnimatedVisibility(visible = state.selectedMembersUiState.isNotEmpty()) {
                        LazyRow(
                            Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(horizontal = SpacingXLarge),
                            horizontalArrangement = Arrangement.spacedBy(SpacingXMedium)
                        ) {
                            items(
                                state.selectedMembersUiState,
                                key = { it.userId }) { selectedMembersUiState ->
                                SelectedMemberItem(
                                    modifier = Modifier.animateItemPlacement(),
                                    painter = painterResource(id = R.drawable.ic_cancel),
                                    imageUrl = selectedMembersUiState.imageUrl,
                                    username = selectedMembersUiState.name,
                                    userId = selectedMembersUiState.userId,
                                    onClickIcon = interaction::onRemoveSelectedItem
                                )
                            }
                        }
                    }
                }
                items(state.membersUiState) { membersUiState ->
                    MemberItem(
                        modifier = Modifier.animateItemPlacement(),
                        painter = painterResource(id = R.drawable.ic_check),
                        chooseMemberUiState = membersUiState
                    ) {
                        interaction.onClickMemberItem(it)
                    }
                }

            }
        }
        if (state.error != null) {
            ActionSnakeBar(
                contentMessage = state.error.toString(),
                isVisible = true,
                isToggleButtonVisible = false
            )
        }
        if (state.successMessage != null) {
            ActionSnakeBar(
                contentMessage = state.successMessage.toString(),
                isVisible = true,
                isToggleButtonVisible = false
            )
        }
    }
}

@Preview
@Composable
fun DMChooseMemberPreview() {
    val viewModel: DMChooseMemberInteraction = hiltViewModel()
    TeamixTheme {
        DirectMessageChooseMemberContent(
            state = DMChooseMemberUiState(),
            viewModel
        )
    }
}