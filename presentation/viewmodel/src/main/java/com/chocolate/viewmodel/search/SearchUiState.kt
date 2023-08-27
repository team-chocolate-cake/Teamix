package com.chocolate.viewmodel.search

import com.chocolate.entities.uills.Empty

data class SearchUiState(
    val query: String = String.Empty,
    val channelsUiState: List<ChannelsUiState> = emptyList(),
    val membersUiState: List<SearchMembersUiState> = emptyList(),
    val recentSearches: List<String> = emptyList(),
    val currentTabIndex: Int = 0,
    val isLoading: Boolean = false,
    val showNoInternetLottie: Boolean = false,
    val error: String? = null
)

data class ChannelsUiState(
    val id: Int = 0,
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
