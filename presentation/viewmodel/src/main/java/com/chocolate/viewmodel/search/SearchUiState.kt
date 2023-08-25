package com.chocolate.viewmodel.search

import com.chocolate.viewmodel.base.BaseViewModel

data class SearchUiState(
    val searchQuery: String = "",
    val channelsUiState: List<ChannelsUiState> = emptyList(),
    val membersUiState: List<MembersUiState> = emptyList(),
    val recentSearches: List<String> = listOf("abc","def","ghi","abv"),
    val isLoading: Boolean = false,
    val error: String? = null
): BaseViewModel.BaseUiState

data class ChannelsUiState(
    val channelId: Int = 0,
    val channelName: String = "",
    val numberOfMembers : Int = 0,
    val isPrivate: Boolean = false,
)

data class MembersUiState(
    val memberId: Int = 0,
    val memberName: String = "",
    val imageUrl: String = "",
    val isActive: Boolean = false
)
