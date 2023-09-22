package com.chocolate.presentation.screens.directMessageChooseMember

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.DMMemberItem
import com.chocolate.presentation.composable.LoadingDialog
import com.chocolate.presentation.composable.NoInternetLottie
import com.chocolate.presentation.composable.SelectedMemberItem
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.screens.directMessageChat.navigateToDmChat
import com.chocolate.presentation.screens.createChannel.composable.ActionSnakeBar
import com.chocolate.presentation.screens.home.navigateToHome
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.directMessageChooseMember.DirectMessageChooseMemberInteraction
import com.chocolate.viewmodel.directMessageChooseMember.DirectMessageChooseMemberUiEffect
import com.chocolate.viewmodel.directMessageChooseMember.DirectMessageChooseMemberUiState
import com.chocolate.viewmodel.directMessageChooseMember.DirectMessageChooseMemberViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DirectMessageChooseMemberScreen(
    viewModel: DirectMessageChooseMemberViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val state by viewModel.state.collectAsState()
    DirectMessageChooseMemberContent(state = state, viewModel , navController)
    LaunchedEffect(key1 = viewModel.effect ){
        viewModel.effect.collectLatest {
            when(it){
                is DirectMessageChooseMemberUiEffect.NavigateToDmChat -> {
                    navController.navigateToDmChat(
                        popBackStack = true,
                        groupId = it.groupId,
                        memberName = state.selectedMembersUiState!!.name,
                    )
                }
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DirectMessageChooseMemberContent(
    state: DirectMessageChooseMemberUiState,
    interaction: DirectMessageChooseMemberInteraction,
    navController: NavController
) {
    val colors = MaterialTheme.customColors()
    val context = LocalContext.current
    val text =
        if (state.selectedMembersUiState ==null) stringResource(R.string.skip) else stringResource(
            R.string.ok
        )
    AnimatedVisibility(state.isLoading) {
        LoadingDialog()
    }
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
                        if (state.selectedMembersUiState != null) {
                            interaction.onClickOk()
                        } else {
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
                    AnimatedVisibility(visible = state.selectedMembersUiState != null) {
                        LazyRow(
                            Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(horizontal = SpacingXLarge),
                            horizontalArrangement = Arrangement.spacedBy(SpacingXMedium)
                        ) {
                            item {
                                SelectedMemberItem(
                                    modifier = Modifier.animateItemPlacement(),
                                    painter = painterResource(id = R.drawable.ic_cancel),
                                    imageUrl = state.selectedMembersUiState!!.imageUrl,
                                    username = state.selectedMembersUiState!!.name,
                                    userId = state.selectedMembersUiState!!.userId,
                                    onClickIcon = interaction::onRemoveSelectedItem
                                )
                            }
                        }
                    }
                }
                items(state.membersUiState) { membersUiState ->
                    DMMemberItem(
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
    val viewModel: DirectMessageChooseMemberInteraction = hiltViewModel()
    val navController = LocalNavController.current
    TeamixTheme {
        DirectMessageChooseMemberContent(
            state = DirectMessageChooseMemberUiState(),
            viewModel,
            navController
        )
    }
}