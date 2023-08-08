package com.chocolate.viewmodel.addMember

import com.chocolate.viewmodel.allMembers.MemberItemUiState
import com.chocolate.viewmodel.base.BaseErrorUiState

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

