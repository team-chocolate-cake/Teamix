package com.chocolate.repository.mappers.users

import com.chocolate.entities.user.User
import com.chocolate.repository.model.dto.users.response.OwnerUserDto

fun OwnerUserDto.toEntity(): User {
    return User(
        imageUrl = this.avatarUrl ?: "",
        email = this.email ?: "",
        fullName = this.fullName ?: "",
        role = this.role ?: 0,
        id = this.userId ?: 0
    )
}