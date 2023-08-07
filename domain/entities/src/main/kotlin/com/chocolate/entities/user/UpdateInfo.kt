package com.chocolate.entities.user

data class UpdateInfo(
    val fullName: String,
    val role: Int ,
    val profileData: List<ProfileData>
)

data class ProfileData(
    val id: Int,
    val value: String
)