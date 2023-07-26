package com.chocolate.remote.messages.request

data class MatchNarrowRequest(
    val msg_ids: List<Int>,
    val narrow: List<NarrowItem>
){
    data class NarrowItem(
        val operator: String,
        val operand: String
    )
}