package com.chocolate.viewmodel.search

import com.chocolate.entities.user.User

fun List<User>.toMembersUiState(): List<MembersUiState>{
    return this.map { it.toMemberUiState()}
}

fun User.toMemberUiState(): MembersUiState {
    return MembersUiState(
        memberId = this.id,
        imageUrl = this.imageUrl,
        memberName = this.fullName,
    )
}