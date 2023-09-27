package com.chocolate.viewmodel.addmember

import com.chocolate.entities.utils.Empty
import com.chocolate.viewmodel.allMembers.MemberItemUiState
import com.chocolate.viewmodel.base.BaseErrorUiState

data class AddMemberUiState(
    val searchInput: String = String.Empty,
    val selectedMembers: List<MemberItemUiState> = emptyList(),
    val suggestedMembers: List<SuggestedMemberItemUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: BaseErrorUiState = BaseErrorUiState()
)

data class SuggestedMemberItemUiState(
    val imageUrl: String = String.Empty,
    val name: String = String.Empty,
    val jobTitle: String = String.Empty,
    val isSelected: Boolean = false
)