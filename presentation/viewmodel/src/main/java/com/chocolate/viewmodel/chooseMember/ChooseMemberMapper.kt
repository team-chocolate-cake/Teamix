package com.chocolate.viewmodel.chooseMember

import com.chocolate.entities.user.User

fun List<User>.toUsersUiState(): List<MembersUiState>{
    return this.map { it.toUserUiState()}
}

fun User.toUserUiState(): MembersUiState{
    return MembersUiState(
        userId = this.id,
        imageUrl = this.imageUrl,
        name = this.fullName,
        status = this.status,
    )
}