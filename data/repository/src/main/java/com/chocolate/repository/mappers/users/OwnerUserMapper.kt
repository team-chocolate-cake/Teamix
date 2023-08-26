package com.chocolate.repository.mappers.users

import com.chocolate.entities.user.User
import com.chocolate.entities.user.UserRole
import com.chocolate.repository.model.dto.users.response.OwnerUserDto

fun OwnerUserDto.toEntity(): User {
    return User(
        imageUrl = this.avatarUrl.orEmpty(),
        email = this.email.orEmpty(),
        fullName = this.fullName.orEmpty(),
        role = UserRole.fromValue(this.role ?: 400),
        id = this.userId ?: 0,
        status = ""
    )
}