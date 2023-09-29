package com.chocolate.viewmodel.topicmessages

import com.chocolate.entities.util.Empty

data class TopicUiState(
    val messages: List<MessageUiState> = emptyList(),
    val topicName: String = String.Empty,
    val messageInput: String = String.Empty,
    val error: String? = null,
)

data class MessageUiState(
    val id:Int=0,
    val username: String = String.Empty,
    val userId: String = String.Empty,
    val replayDate: String = String.Empty,
    val userImage: String = String.Empty,
    val messageImageUrl: String = String.Empty,
    val reactions: List<ReactionUiState> = emptyList(),
    val message: String = String.Empty,
)

data class ReactionUiState(
    val reaction: Int = -1,
    val count: Int = 0,
    val clicked:Boolean = false
)
