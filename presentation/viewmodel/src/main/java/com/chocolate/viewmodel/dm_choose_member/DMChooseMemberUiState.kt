package com.chocolate.viewmodel.dm_choose_member

import com.chocolate.entities.uills.Empty
import com.chocolate.viewmodel.chooseMember.ChooseMembersUiState

data class DMChooseMemberUiState(
    val searchQuery: String = String.Empty,
    val selectedMembersUiState: List<SelectedMembersUiState> = emptyList(),
    val membersUiState: List<ChooseMembersUiState> = emptyList(),
    val channelName: String = String.Empty,
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val error: String? = null
)

data class SelectedMembersUiState(
    val userId: Int = 0,
    val imageUrl: String =String.Empty,
    val name: String = String.Empty,
    val isClickedForRemoval: Boolean = false
)