package com.chocolate.viewmodel.allMembers

import com.chocolate.entities.utils.Empty
import com.chocolate.viewmodel.base.BaseErrorUiState

data class AllMembersUiState(
    val searchInput: String = String.Empty,
    val members: List<MemberItemUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: BaseErrorUiState = BaseErrorUiState()
)

data class MemberItemUiState(
    val imageUrl: String = String.Empty,
    val name: String = String.Empty,
    val jobTitle: String = String.Empty
)
