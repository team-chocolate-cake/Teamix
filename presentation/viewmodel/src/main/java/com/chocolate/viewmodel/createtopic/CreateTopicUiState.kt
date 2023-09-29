package com.chocolate.viewmodel.createtopic

import com.chocolate.entities.util.Empty

data class CreateTopicUiState(
    val name: String = String.Empty,
    val content: String = String.Empty,
    val error: String? = null,
    val isLoading: Boolean = false,
)

