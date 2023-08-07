package com.chocolate.presentation.screens.allMembers

import com.chocolate.presentation.base.BaseErrorUiState

data class AllMembersUiState(
    val searchInput: String = "",
    val members: List<MemberItemUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: BaseErrorUiState = BaseErrorUiState()
)

data class MemberItemUiState(
    val imageUrl: String = "",
    val name: String = "",
    val jobTitle: String = ""
)
