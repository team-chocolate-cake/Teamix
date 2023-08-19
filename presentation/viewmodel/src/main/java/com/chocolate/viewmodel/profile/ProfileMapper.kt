package com.chocolate.viewmodel.profile

import com.chocolate.entities.user.User
import com.chocolate.entities.user.UserRole

fun User.toOwnerUserUiState(): ProfileUiState{
    val roleEnum = UserRole.values().find { it.value == this.role }
    val roleString = roleEnum?.stringValue ?: "Guest"
    return ProfileUiState(
        image = this.imageUrl,
        name = this.fullName,
        email = this.email,
        role = roleString
    )
}