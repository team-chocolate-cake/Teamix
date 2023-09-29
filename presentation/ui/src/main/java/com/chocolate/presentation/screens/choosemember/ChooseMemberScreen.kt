package com.chocolate.presentation.screens.choosemember

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
import com.chocolate.presentation.screens.createchannel.composable.ActionSnakeBar
import com.chocolate.presentation.screens.home.navigateToHome
import com.chocolate.presentation.theme.LightCard
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingMedium
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.CollectUiEffect
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.choosemember.ChooseMemberInteraction
import com.chocolate.viewmodel.choosemember.ChooseMemberUiEffect
import com.chocolate.viewmodel.choosemember.ChooseMemberUiState
import com.chocolate.viewmodel.choosemember.ChooseMemberViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ChooseMemberScreen(
    viewModel: ChooseMemberViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val navController = LocalNavController.current

    CollectUiEffect(viewModel.effect) { effect ->
        when (effect) {
            is ChooseMemberUiEffect.NavigateToHome -> navController.navigateToHome()
        }
    }

    ChooseMemberContent(state = state, viewModel)

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChooseMemberContent(
    state: ChooseMemberUiState,
    chooseMemberInteraction: ChooseMemberInteraction
) {
    val colors = MaterialTheme.customColors()
    val searchQuery by state.searchQuery.collectAsState()
    val systemUiController = rememberSystemUiController()
    val isDarkIcons = MaterialTheme.customColors().card == LightCard
    TeamixScaffold(
        containerColorAppBar = colors.card,
        hasAppBar = true,
        hasBackArrow = true,
        actionsAppbar = {
            Text(
                text = state.actionBarActionText,
                style = MaterialTheme.typography.bodyMedium,
                color = colors.primary,
                modifier = Modifier
                    .padding(end = SpacingMedium)
                    .clickable {
                        chooseMemberInteraction.onActionBarTextClick()
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
        onRetry = { chooseMemberInteraction.onClickRetry() },
        onError = {
            NoInternetLottie(
                text = stringResource(id = R.string.no_internet_connection),
                isShow = state.error != null && state.successMessage == null,
                isDarkMode = isSystemInDarkTheme(),
            )
        }
    ) { paddingValues ->
        systemUiController.setStatusBarColor(MaterialTheme.customColors().card, darkIcons = isDarkIcons)
        Box {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(vertical = SpacingXLarge),
                verticalArrangement = Arrangement.spacedBy(SpacingXLarge)
            ) {
                item {
                    TeamixTextField(
                        value = searchQuery,
                        modifier = Modifier.padding(horizontal = SpacingXLarge),
                        hint = stringResource(id = R.string.search),
                        onValueChange = { chooseMemberInteraction.onChangeSearchQuery(it) },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.search),
                                contentDescription = null
                            )
                        })
                }
                item {
                    AnimatedVisibility(visible = !state.hasNoSelectedMember) {
                        LazyRow(
                            Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(horizontal = SpacingXLarge),
                            horizontalArrangement = Arrangement.spacedBy(SpacingMedium)
                        ) {
                            items(
                                state.selectedMembers,
                                key = { it.memberId }
                            ) { selectedMembersUiState ->
                                SelectedMemberItem(
                                    modifier = Modifier.animateItemPlacement(),
                                    painter = painterResource(id = R.drawable.ic_cancel),
                                    imageUrl = selectedMembersUiState.imageUrl,
                                    username = selectedMembersUiState.name,
                                    userId = selectedMembersUiState.memberId
                                ) { chooseMemberInteraction.onRemoveSelectedItem(it) }
                            }
                        }
                    }
                }
                items(state.membersItemUiState, key = { it.memberId }) { membersUiState ->
                    MemberItem(
                        modifier = Modifier.animateItemPlacement(),
                        painter = painterResource(id = R.drawable.ic_check),
                        chooseMemberUiState = membersUiState
                    ) { chooseMemberInteraction.onClickMemberItem(membersUiState) }
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
fun ChooseMemberPreview() {
    val viewModel: ChooseMemberViewModel = hiltViewModel()
    TeamixTheme {
        ChooseMemberContent(
            state = ChooseMemberUiState(),
            viewModel
        )
    }
}