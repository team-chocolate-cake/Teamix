package com.chocolate.remote.users.request

data class UpdateInfo(
    val full_name: String? = null,
    val role: Int? = null,
    val profile_data: List<ProfileData>? = null
)

data class ProfileData(
    val id: Int,
    val value: String
)