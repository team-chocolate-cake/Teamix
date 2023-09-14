package com.chocolate.viewmodel.chooseMember

import com.chocolate.entities.uills.Empty

data class ChooseMemberUiState(
    val searchQuery: String = String.Empty,
    val selectedMembersUiState: List<SelectedMembersUiState> = emptyList(),
    val membersUiState: List<ChooseMembersUiState> = emptyList(),
    val channelName: String = String.Empty,
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val error: String? = null
)

data class SelectedMembersUiState(
    val userId: String = String.Empty,
    val imageUrl: String =String.Empty,
    val name: String = String.Empty,
    val isClickedForRemoval: Boolean = false
)

data class ChooseMembersUiState(
    val userId: String = String.Empty,
    val imageUrl: String =String.Empty,
    val name: String = String.Empty,
    val isSelected: Boolean = false
)