package com.chocolate.viewmodel.profile

import com.chocolate.entities.user.OwnerUser
import com.chocolate.entities.user.Settings
import com.chocolate.entities.user.UserRole

fun OwnerUser.toOwnerUserUiState(): ProfileUiState{
    val roleEnum = UserRole.values().find { it.value == this.role }
    val roleString = roleEnum?.stringValue ?: "Guest"
    return ProfileUiState(
        image = this.avatarUrl,
        name = this.fullName,
        email = this.email,
        role = roleString
    )
}