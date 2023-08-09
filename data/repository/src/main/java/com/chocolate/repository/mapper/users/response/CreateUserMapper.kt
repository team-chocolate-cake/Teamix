package com.chocolate.repository.mapper.users.response

import com.chocolate.entities.user.CreateUser
import com.chocolate.repository.dto.users.response.CreateUserDto

fun CreateUserDto.toCreateUser(): CreateUser {
return CreateUser(
    userId=userId?: 0
)
}