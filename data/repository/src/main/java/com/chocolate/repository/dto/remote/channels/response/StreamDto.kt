package com.chocolate.repository.dto.remote.channels.response

import com.google.gson.annotations.SerializedName

data class StreamDto(

	@field:SerializedName("first_message_id")
	val firstMessageId: Int? = null,
	@field:SerializedName("stream_post_policy")
	val streamPostPolicy: Int? = null,
	@field:SerializedName("is_web_public")
	val isWebPublic: Boolean? = null,
	@field:SerializedName("rendered_description")
	val renderedDescription: String? = null,
	@field:SerializedName("stream_id")
	val streamId: Int? = null,
	@field:SerializedName("name")
	val name: String? = null,
	@field:SerializedName("description")
	val description: String? = null,
	@field:SerializedName("history_public_to_subscribers")
	val historyPublicToSubscribers: Boolean? = null,
	@field:SerializedName("is_announcement_only")
	val isAnnouncementOnly: Boolean? = null,
	@field:SerializedName("message_retention_days")
	val messageRetentionDays: Any? = null,
	@field:SerializedName("can_remove_subscribers_group_id")
	val canRemoveSubscribersGroupId: Int? = null,
	@field:SerializedName("invite_only")
	val inviteOnly: Boolean? = null
)
