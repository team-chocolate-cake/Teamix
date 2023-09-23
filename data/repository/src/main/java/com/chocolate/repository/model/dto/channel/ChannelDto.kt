package com.chocolate.repository.model.dto.channel

data class ChannelDto(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    @field:JvmField val isPrivate: Boolean? = null,
    //val topics: List<Topic>? = null,
    val membersId:List<String>? = null,
)