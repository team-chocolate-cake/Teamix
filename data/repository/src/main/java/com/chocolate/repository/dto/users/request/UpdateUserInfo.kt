package com.chocolate.repository.dto.users.request

data class UpdateInfoDto(
    val fullName: String? = null,
    val role: Int? = null,
    val profileData: List<ProfileDataDto>? = null
)

data class ProfileDataDto(
    val id: Int,
    val value: String
)