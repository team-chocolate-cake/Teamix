package com.chocolate.viewmodel.chooseMember

import com.chocolate.entities.member.Member

fun List<Member>.toUsersUiState(): List<ChooseMembersUiState>{
    return this.map { it.toUserUiState()}
}

fun Member.toUserUiState(): ChooseMembersUiState{
    return ChooseMembersUiState(
        userId = 0,
        imageUrl = this.imageUrl,
        name = this.name,
    )
}