package com.chocolate.entities

import com.chocolate.entities.channel.Channel
import com.chocolate.entities.user.Member

data class Organization(
    val id: String,
    val name: String,
    val imageUrl: String,
    val invitationCode: String,
    val owner: Member,
    val members: List<Member>,
    val channels: List<Channel>,
)
