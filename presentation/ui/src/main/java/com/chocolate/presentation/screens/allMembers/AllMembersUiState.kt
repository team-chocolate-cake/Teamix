package com.chocolate.presentation.screens.allMembers

data class AllMembersUiState(
    val members: List<MemberItemUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: ErrorUiState = ErrorUiState()
)

data class MemberItemUiState(
    val imageUrl: String = "",
    val name: String = "",
    val jobTitle: String = ""
)

data class ErrorUiState(
    val message: String = "",
    val isError: Boolean = false
)
