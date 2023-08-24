package com.chocolate.repository.mappers.users

import com.chocolate.entities.user.User
import com.chocolate.entities.user.UserRole
import com.chocolate.repository.model.dto.users.response.MemberDto
import com.chocolate.repository.model.dto.users.response.UserDto
import com.chocolate.repository.model.localDto.users.UserLocalDto


fun UserDto.toEntity(): User {
    return User(
        imageUrl = this.userDetailsDto?.avatarUrl ?: "",
        email = this.userDetailsDto?.email ?: "",
        fullName = this.userDetailsDto?.fullName ?: "",
        role = UserRole.fromValue(this.userDetailsDto?.role ?: 100),
        id = this.userDetailsDto?.userId ?: 0,
        status = ""
    )
}

fun UserLocalDto.toEntity(): User {
    return User(
        imageUrl = this.imageUrl,
        email = this.email,
        fullName = this.username,
        role = UserRole.fromValue(role),
        id = this.userId,
        status = ""
    )
}

fun User.toLocalDto(): UserLocalDto{
    return UserLocalDto(
        imageUrl = this.imageUrl,
        email = this.email,
        username = this.fullName,
        role = this.role.value,
        userId = this.id
    )
}

fun MemberDto.toEntity(): User = User(
    imageUrl = avatarUrl.orEmpty(),
    email = email.orEmpty(),
    fullName = fullName.orEmpty(),
    role = UserRole.fromValue(this.role ?: 100),
    id = userId?:-1,
    status = ""
)

fun List<MemberDto>?.toEntity(): List<User> = this?.map { it.toEntity() }.orEmpty()