package com.chocolate.entities.channel_models

data class StreamInfo(
    val firstMessageId: Int,
    val streamPostPolicy: Int,
    val isWebPublic: Boolean,
    val renderedDescription: String,
    val streamId: Int,
    val name: String,
    val description: String,
    val historyPublicToSubscribers: Boolean,
    val isAnnouncementOnly: Boolean,
    val messageRetentionDays: Any,
    val canRemoveSubscribersGroupId: Int,
    val inviteOnly: Boolean,
)
