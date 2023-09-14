package com.chocolate.viewmodel.createTopic

import com.chocolate.entities.uills.Empty

data class CreateTopicUiState(
    val name: String = String.Empty,
    val content: String = String.Empty,
    val message: String? = null,
    val isLoading: Boolean = false,
)
