package com.chocolate.viewmodel.directMessage

import com.chocolate.entities.uills.Empty

data class DirectMessageUiState(
    val membersUiState: List<MembersUiState> = emptyList(),
    val isLoading: Boolean = false,
    val showNoInternetLottie: Boolean = false,
    val error: String? = null
){
    data class MembersUiState(
        val id: Int = 0,
        val name: String = String.Empty,
        val imageUrl: String = String.Empty,
        val isOnline: Boolean = false
    )
}
