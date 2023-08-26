package com.chocolate.viewmodel.chooseMember

import com.chocolate.entities.user.User

fun List<User>.toUsersUiState(): List<ChooseMembersUiState>{
    return this.map { it.toUserUiState()}
}

fun User.toUserUiState(): ChooseMembersUiState{
    return ChooseMembersUiState(
        userId = this.id,
        imageUrl = this.imageUrl,
        name = this.fullName,
        status = this.status,
    )
}