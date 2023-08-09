package com.chocolate.repository.model.dto.users.request

data class UpdateInfo(
    val fullName: String? = null,
    val role: Int? = null,
    val profileData: List<com.chocolate.repository.model.dto.users.request.ProfileData>? = null
)

data class ProfileData(
    val id: Int,
    val value: String
)