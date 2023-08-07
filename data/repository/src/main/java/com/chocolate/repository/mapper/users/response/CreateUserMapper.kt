package com.chocolate.repository.mapper.users.response

import com.chocolate.entities.user.respons.CreateUser
import com.chocolate.repository.dto.users.response.CreateUserDto

fun CreateUserDto.toCreateUser(): CreateUser {

return CreateUser(
    msg=msg, userId=userId
)
}