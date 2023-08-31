package com.chocolate.repository.model.dto.users.response

data class UserDataDto(
    val id: Int? = 0,
    val name: String? = "",
    val role: String? = "",
    val email: String? = "",
    val imageUrl: String? = ""
)