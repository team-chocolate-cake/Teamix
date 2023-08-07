package com.chocolate.presentation.screens.allMembers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chocolate.presentation.screens.allMembers.composable.MemberItem
import com.chocolate.presentation.screens.allMembers.composable.SearchBox
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors

@Composable
fun AllMembersScreen(
    //navController: NavController,

    ) {
    AllMembersContent(state = allMembersUiState)
}

@Composable
private fun AllMembersContent(
    state: AllMembersUiState
) {
    val colors = MaterialTheme.customColors()

    TeamixTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colors.background)
                .padding(Space16),
        ) {
            SearchBox()
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(Space8),
                contentPadding = PaddingValues(vertical = Space16)
            ) {
                items(state.members.count()) { index ->
                    MemberItem(
                        personImageUrl = state.members[index].imageUrl,
                        name = state.members[index].name,
                        jobTitle = state.members[index].jobTitle
                    )
                }
            }
        }
    }
}

