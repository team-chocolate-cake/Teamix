package com.chocolate.repository.model.dto.users.request

data class UpdateUserInfoDto(
    val fullName: String? = null,
    val role: Int? = null,
    val profileData: List<ProfileDataDto>? = null
)

data class ProfileDataDto(
    val id: Int,
    val value: String
)