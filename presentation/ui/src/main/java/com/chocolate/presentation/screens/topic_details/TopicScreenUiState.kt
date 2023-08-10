package com.chocolate.presentation.screens.topic_details

data class TopicScreenUiState(
    val photoAndVideo: List<PhotoOrVideoUiState> = emptyList(),
    val messages: List<MessageUiState> = emptyList(),
    val topicName: String = "TopicName",
    val messageInput: String = ""
)

data class PhotoOrVideoUiState(
    var isSelected: Boolean,
    val file: String,
    val isLoading:Boolean = false,
    val error : String? = null
)

data class MessageUiState(
    val username: String = "",
    val replayDate: String = "",
    val userImage: String = "",
    val messageImage: String? = null,
    val reactions: List<ReactionUiState> = emptyList(),
    val isMyReplay: Boolean = false,
    val message: String = "",
)

data class ReactionUiState(
    val reaction: Int = -1,
    val count: Int = 0,
    val clicked:Boolean = false
)
