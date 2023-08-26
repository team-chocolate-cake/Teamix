package com.chocolate.viewmodel.search

import com.chocolate.entities.user.User

fun List<User>.toMembersUiState(): List<SearchMembersUiState>{
    return this.map { it.toMemberUiState()}
}

fun User.toMemberUiState(): SearchMembersUiState {
    return SearchMembersUiState(
        id = this.id,
        imageUrl = this.imageUrl,
        name = this.fullName,
    )
}