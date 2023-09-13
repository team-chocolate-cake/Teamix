package com.chocolate.viewmodel.topic

import com.chocolate.entities.uills.Empty

data class TopicUiState(
    val photoAndVideo: List<PhotoOrVideoUiState> = emptyList(),
    val messages: List<MessageUiState> = emptyList(),
    val topicName: String = String.Empty,
    val messageInput: String = String.Empty,
    val error: String? = null,
)

data class PhotoOrVideoUiState(
    val isSelected: Boolean = false,
    val file: String = String.Empty,
    val isLoading:Boolean = false,
    val error : String? = null
)

data class MessageUiState(
    val id:Int=0,
    val username: String = String.Empty,
    val replayDate: String = String.Empty,
    val userImage: String = String.Empty,
    val messageImageUrl: String = String.Empty,
    val reactions: List<ReactionUiState> = emptyList(),
    val isMyReplay: Boolean = false,
    val message: String = String.Empty,
)

data class ReactionUiState(
    val reaction: Int = -1,
    val count: Int = 0,
    val clicked:Boolean = false
)
