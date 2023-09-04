package com.chocolate.viewmodel.profile

import com.chocolate.entities.user.User

fun User.toOwnerUserUiState(): ProfileUiState{
    val roleEnum = this.role
    val roleString = roleEnum.stringValue
    return ProfileUiState(
        id = this.id,
        imageUrl = this.imageUrl,
        name = this.fullName,
        email = this.email,
        role = roleString
    )
}