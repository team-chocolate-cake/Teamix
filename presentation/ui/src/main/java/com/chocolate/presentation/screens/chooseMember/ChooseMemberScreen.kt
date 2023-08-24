package com.chocolate.presentation.screens.chooseMember

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material3.Divider
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
import com.chocolate.presentation.composable.SelectedMemberItem
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.composable.TeamixTextField
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.chooseMember.ChooseMemberInteraction
import com.chocolate.viewmodel.chooseMember.ChooseMemberUiState
import com.chocolate.viewmodel.chooseMember.ChooseMemberViewModel

@Composable
fun ChooseMemberScreen(
    viewModel: ChooseMemberViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    ChooseMemberContent(state = state, viewModel)

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChooseMemberContent(
    state: ChooseMemberUiState,
    chooseMemberInteraction: ChooseMemberInteraction
) {
    val colors = MaterialTheme.customColors()
    val text =
        if (state.selectedMembersUiState.isEmpty()) stringResource(R.string.skip) else stringResource(
            R.string.ok
        )
    TeamixScaffold(
        isDarkMode = isSystemInDarkTheme(),
        containerColorAppBar = colors.border,
        hasAppBar = true,
        hasBackArrow = true,
        actionsAppbar = {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = colors.primary,
                modifier = Modifier.padding(end = Space8)
            )
        },
        title = stringResource(R.string.choose_members),
        isLoading = state.isLoading,
        onLoading = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator(color = colors.primary) }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(vertical = Space16),
            verticalArrangement = Arrangement.spacedBy(Space16)
        ) {
            item {
                TeamixTextField(value = state.searchQuery,
                    modifier = Modifier.padding(horizontal = Space16),
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
                AnimatedVisibility(visible = state.selectedMembersUiState.isNotEmpty()) {
                    LazyRow(
                        Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = Space16),
                        horizontalArrangement = Arrangement.spacedBy(Space8)
                    ) {
                        items(
                            state.selectedMembersUiState,
                            key = { it.userId }) { selectedMembersUiState ->
                            SelectedMemberItem(
                                modifier = Modifier.animateItemPlacement(),
                                selectedMembersUiState
                            )
                        }
                    }
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = Space16), color = colors.border
                    )
                }
            }
            items(state.membersUiState, key = { it.userId }) { membersUiState ->
                MemberItem(
                    modifier = Modifier.animateItemPlacement(),
                    membersUiState = membersUiState,
                    onClickMemberItem = { chooseMemberInteraction.onClickMemberItem(it) }
                )
            }

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