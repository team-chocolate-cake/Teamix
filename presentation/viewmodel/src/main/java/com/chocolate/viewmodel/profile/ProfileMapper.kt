package com.chocolate.viewmodel.profile

import com.chocolate.entities.Member

fun Member.toOwnerUserUiState(): ProfileUiState{
    val roleEnum = this.role
    //val roleString = roleEnum.stringValue
    return ProfileUiState(
        id = 0,
        imageUrl = this.imageUrl,
        name = this.name,
        email = this.email,
        role = role.value
    )
}