package com.chocolate.presentation.screens.addMember

import com.chocolate.presentation.base.BaseErrorUiState
import com.chocolate.presentation.screens.allMembers.MemberItemUiState

data class AddMemberUiState(
    val searchInput: String = "",
    val selectedMembers: List<MemberItemUiState> = emptyList(),
    val suggestedMembers: List<SuggestedMemberItemUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: BaseErrorUiState = BaseErrorUiState()
)

data class SuggestedMemberItemUiState(
    val imageUrl: String = "",
    val name: String = "",
    val jobTitle: String = "",
    val isSelected: Boolean = false
)

