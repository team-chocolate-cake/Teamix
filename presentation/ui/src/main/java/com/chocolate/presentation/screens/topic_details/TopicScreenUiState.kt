package com.chocolate.presentation.screens.topic_details

data class TopicScreenUiState(
    val photoAndVideo: List<PhotoOrVideoUiState> = emptyList(),
    val messages: List<MessageUiState> = emptyList(),
    val topicName: String = "TopicName",
    val messageInput: String = ""
)

data class PhotoOrVideoUiState(
    var isSelected: Boolean,
    val file: String
)

data class MessageUiState(
    val username: String,
    val replayDate: String,
    val userImage: String,
    val messageImage: String? = null,
    val reactions: List<ReactionUiState> = emptyList(),
    val isMyReplay: Boolean,
    val message: String,
)

data class ReactionUiState(
    val reaction: Int,
    val count: Int,
    val clicked:Boolean = false
)
