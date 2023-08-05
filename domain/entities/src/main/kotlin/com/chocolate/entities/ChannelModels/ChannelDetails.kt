package com.chocolate.entities.ChannelModels

data class ChannelDetails(
    val firstMessageId: Int? = null,
    val streamPostPolicy: Int? = null,
    val isWebPublic: Boolean? = null,
    val renderedDescription: String? = null,
    val streamId: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val historyPublicToSubscribers: Boolean? = null,
    val isAnnouncementOnly: Boolean? = null,
    val messageRetentionDays: Any? = null,
    val canRemoveSubscribersGroupId: Int? = null,
    val inviteOnly: Boolean? = null
)
