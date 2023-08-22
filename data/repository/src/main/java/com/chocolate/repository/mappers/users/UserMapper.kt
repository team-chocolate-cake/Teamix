package com.chocolate.repository.mappers.users

import com.chocolate.entities.user.User
import com.chocolate.repository.model.dto.users.response.MemberDto
import com.chocolate.repository.model.dto.users.response.UserDto
import com.chocolate.repository.model.localDto.users.UserLocalDto


fun UserDto.toUser(): User {
    return User(
        imageUrl = this.userDetailsDto?.avatarUrl ?: "",
        email = this.userDetailsDto?.email ?: "",
        fullName = this.userDetailsDto?.fullName ?: "",
        role = this.userDetailsDto?.role ?: 0,
        userId = this.userDetailsDto?.userId ?: 0
    )
}

fun UserLocalDto.toCurrentUser(): User {
    return User(
        imageUrl = this.imageUrl,
        email = this.email,
        fullName = this.username,
        role = this.role,
        userId = this.userId
    )
}

fun User.toCurrentUserLocal(): UserLocalDto{
    return UserLocalDto(
        imageUrl = this.imageUrl,
        email = this.email,
        username = this.fullName,
        role = this.role,
        userId = this.userId
    )
}

fun MemberDto.toUser(): User = User(
    imageUrl = avatarUrl.orEmpty(),
    email = email.orEmpty(),
    fullName = fullName.orEmpty(),
    role = role?:100,
    userId = userId?:-1,
    status = "",
    away = false
)

fun List<MemberDto>?.toUsers(): List<User> = this?.map { it.toUser() }?: emptyList()