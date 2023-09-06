package com.chocolate.entities.organization

import com.chocolate.entities.user.User

data class Organization(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val invitationCode: String,
    val owner: User,
    val members: List<String>
)
