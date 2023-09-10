package com.chocolate.entities.channel

enum class MutingStatus(val value: String) {
    MUTE("add"),
    UN_MUTE("remove")
}

enum class ChannelType {
    ARCHIVED,
    PUBLIC,
    PRIVATE
}