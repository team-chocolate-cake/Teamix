package com.chocolate.remote.users.request

data class UpdateInfo(
    val fullName: String? = null,
    val role: Int? = null,
    val profileData: List<ProfileData>? = null
)

data class ProfileData(
    val id: Int,
    val value: String
)