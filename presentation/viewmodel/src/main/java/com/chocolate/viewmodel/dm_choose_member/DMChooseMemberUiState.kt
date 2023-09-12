package com.chocolate.viewmodel.dm_choose_member

import com.chocolate.entities.uills.Empty
import com.chocolate.viewmodel.chooseMember.ChooseMembersUiState
import com.chocolate.viewmodel.chooseMember.SelectedMembersUiState

data class DMChooseMemberUiState(
    val searchQuery: String = String.Empty,
    val selectedMembersUiState: List<SelectedMembersUiState> = emptyList(),
    val membersUiState: List<ChooseMembersUiState> = emptyList(),
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val error: String? = null
)