package com.chocolate.viewmodel.directMessage

import com.chocolate.entities.user.User

fun List<User>.toUiState(): List<DirectMessageUiState.MembersUiState>{
    return this.map { it.toUiState()}
}

fun User.toUiState(): DirectMessageUiState.MembersUiState {
    return DirectMessageUiState.MembersUiState(
        id = this.id,
        imageUrl = this.imageUrl,
        name = this.fullName,
    )
}