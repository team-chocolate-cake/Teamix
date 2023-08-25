package com.chocolate.viewmodel.search

import com.chocolate.viewmodel.base.BaseViewModel

data class SearchUiState(
    val query: String = "",
    val channelsUiState: List<ChannelsUiState> = emptyList(),
    val membersUiState: List<MembersUiState> = emptyList(),
    val recentSearches: List<String> = listOf("abc","def","ghi","abv"),
    val currentTabIndex: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
): BaseViewModel.BaseUiState

data class ChannelsUiState(
    val id: Int = 0,
    val name: String = "",
    val numberOfMembers : Int = 0,
    val isPrivate: Boolean = false,
)

data class MembersUiState(
    val id: Int = 0,
    val name: String = "",
    val imageUrl: String = "",
    val isActive: Boolean = false
)
