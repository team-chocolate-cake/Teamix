package com.chocolate.entities.user

data class Users(
    val member: List<Member>,
)

data class Member(
    val avatarUrl: String,
    val dateJoined: String,
    val email: String,
    val fullName: String,
    val role: Int,
    val userId: Int
)
