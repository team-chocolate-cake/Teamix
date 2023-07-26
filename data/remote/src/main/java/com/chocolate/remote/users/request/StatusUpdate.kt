package com.chocolate.remote.users.request

data class StatusUpdate(
    val status_text: String? = null,
    val emoji_name: String? = null,
    val emoji_code: String? = null,
    val reaction_type: String? = null,
    val away: Boolean? = null
)