package com.chocolate.entities

import com.chocolate.entities.user.Member

data class Organization(
    val id: String,
    val name: String,
    val imageUrl: String,
    val invitationCode: String,
    val owner: Member,
    val members: List<String>,
    val channels: List<String>,
)
