package com.chocolate.repository.dto.remote.channels.response

import com.google.gson.annotations.SerializedName

data class SubscriptionsItemDto(

	@field:SerializedName("push_notifications")
	val pushNotifications: Boolean? = null,
	@field:SerializedName("desktop_notifications")
	val desktopNotifications: Boolean? = null,
	@field:SerializedName("email_address")
	val emailAddress: String? = null,
	@field:SerializedName("color")
	val color: String? = null,
	@field:SerializedName("pin_to_top")
	val pinToTop: Boolean? = null,
	@field:SerializedName("stream_id")
	val streamId: Int? = null,
	@field:SerializedName("subscribers")
	val subscribers: List<Int?>? = null,
	@field:SerializedName("name")
	val name: String? = null,
	@field:SerializedName("description")
	val description: String? = null,
	@field:SerializedName("is_muted")
	val isMuted: Boolean? = null,
	@field:SerializedName("audible_notifications")
	val audibleNotifications: Boolean? = null,
	@field:SerializedName("invite_only")
	val inviteOnly: Boolean? = null
)
