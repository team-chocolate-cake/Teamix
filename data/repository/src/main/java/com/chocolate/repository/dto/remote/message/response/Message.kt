package com.chocolate.repository.dto.remote.message.response


import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("client")
    val client: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("content_type")
    val contentType: String?,
    @SerializedName("display_recipient")
    val displayRecipient: Any?,
    @SerializedName("flags")
    val flags: List<String?>?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("is_me_message")
    val isMeMessage: Boolean?,
    @SerializedName("reactions")
    val reactions: List<Any?>?,
    @SerializedName("recipient_id")
    val recipientId: Int?,
    @SerializedName("sender_email")
    val senderEmail: String?,
    @SerializedName("sender_full_name")
    val senderFullName: String?,
    @SerializedName("sender_id")
    val senderId: Int?,
    @SerializedName("sender_realm_str")
    val senderRealmStr: String?,
    @SerializedName("stream_id")
    val streamId: Int?,
    @SerializedName("subject")
    val subject: String?,
    @SerializedName("submessages")
    val subMessages: List<Any?>?,
    @SerializedName("timestamp")
    val timestamp: Int?,
    @SerializedName("topic_links")
    val topicLinks: List<Any?>?,
    @SerializedName("type")
    val type: String?
)