package com.chocolate.remote.messages.request

data class MatchNarrowRequest(
    val msg_ids: List<Int>,
    val narrow: List<NarrowItemDto>
)
data class NarrowItemDto(
    val operator: String,
    val operand: String
)