package com.chocolate.viewmodel.search

import com.chocolate.entities.uills.Empty

data class SearchUiState(
    val query: String = String.Empty,
    val channelsUiState: List<SearchChannelUiState> = emptyList(),
    val isLoading: Boolean = false,
    val isDarkTheme: Boolean = false,
    val showNoInternetLottie: Boolean = false,
    val error: String? = null
)

data class SearchChannelUiState(
    val id: String = String.Empty,
    val name: String = String.Empty,
    val numberOfMembers : Int = 0,
    val isPrivate: Boolean = false,
)

data class SearchMembersUiState(
    val id: Int = 0,
    val name: String = String.Empty,
    val imageUrl: String = String.Empty,
    val isOnline: Boolean = false
)
