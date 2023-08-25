package com.chocolate.viewmodel.topic

import com.chocolate.viewmodel.base.BaseViewModel

data class TopicUiState(
    val photoAndVideo: List<PhotoOrVideoUiState> = emptyList(),
    val messages: List<MessageUiState> = emptyList(),
    val topicName: String = "TopicName",
    val messageInput: String = ""
): BaseViewModel.BaseUiState

data class PhotoOrVideoUiState(
    val isSelected: Boolean = false,
    val file: String = "",
    val isLoading:Boolean = false,
    val error : String? = null
)

data class MessageUiState(
    val username: String = "",
    val replayDate: String = "",
    val userImage: String = "",
    val messageImageUrl: String = "",
    val reactions: List<ReactionUiState> = emptyList(),
    val isMyReplay: Boolean = false,
    val message: String = "",
)

data class ReactionUiState(
    val reaction: Int = -1,
    val count: Int = 0,
    val clicked:Boolean = false
)
