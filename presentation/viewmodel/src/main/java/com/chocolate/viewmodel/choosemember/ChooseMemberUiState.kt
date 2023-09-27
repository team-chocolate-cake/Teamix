package com.chocolate.viewmodel.choosemember

import com.chocolate.entities.util.Empty
import kotlinx.coroutines.flow.MutableStateFlow

data class ChooseMemberUiState(
    val searchQuery: MutableStateFlow<String> = MutableStateFlow(String.Empty),
    val selectedMembers: List<SelectedMemberItemUiState> = emptyList(),
    val membersItemUiState: List<SelectedMemberItemUiState> = emptyList(),
    val hasNoSelectedMember: Boolean = true,
    val channelName: String = String.Empty,
    val isLoading: Boolean = true,
    val actionBarActionText: String = "",
    val successMessage: String? = null,
    val error: String? = null
)

data class SelectedMemberItemUiState(
    val memberId: String = String.Empty,
    val imageUrl: String =String.Empty,
    val name: String = String.Empty,
    val isSelected: Boolean = false
)