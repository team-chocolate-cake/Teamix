package com.chocolate.viewmodel.createtopic

data class CreateTopicUiState(
    val content: String = "",
    val topicName: String = "",
    val isLoading: Boolean = false,
    val stringError: String? = null
)