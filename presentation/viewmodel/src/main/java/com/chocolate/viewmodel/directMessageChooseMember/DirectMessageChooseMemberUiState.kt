package com.chocolate.viewmodel.directMessageChooseMember

import com.chocolate.entities.member.Member
import com.chocolate.entities.uills.Empty

data class DirectMessageChooseMemberUiState(
    val selectedMembersUiState: DMChooseMembersUiState? = null,
    val membersUiState: List<DMChooseMembersUiState> = emptyList(),
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val error: String? = null
)
data class DMChooseMembersUiState(
    val userId: String = "",
    val imageUrl: String =String.Empty,
    val name: String = String.Empty,
    val isSelected: Boolean = false
)

fun List<Member>.toDMMemberUiState(): List<DMChooseMembersUiState>{
    return this.map { it.toDMMemberUiState()}
}

fun Member.toDMMemberUiState(): DMChooseMembersUiState{
    return DMChooseMembersUiState(
        userId = this.id,
        imageUrl = this.imageUrl,
        name = this.name,
    )
}