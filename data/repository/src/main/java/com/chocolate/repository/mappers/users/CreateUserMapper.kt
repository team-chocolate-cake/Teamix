package com.chocolate.repository.mappers.users

import com.chocolate.entities.user.CreateUser
import com.chocolate.repository.model.dto.users.response.CreateUserDto

fun CreateUserDto.toCreateUser(): CreateUser {
    return CreateUser(
        userId = userId ?: 0
    )
}