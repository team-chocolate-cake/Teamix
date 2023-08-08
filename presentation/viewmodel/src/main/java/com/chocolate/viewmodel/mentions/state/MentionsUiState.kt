package com.chocolate.viewmodel.mentions.state

data class MentionsUiState(
    val days: List<MentionDaysUiState> = listOf(),
)

data class MentionDaysUiState(
    val day: String = "",
    val mentionInfo: List<MentionInfoUiState> = listOf()
)

data class MentionInfoUiState(
    val image: String,
    val name: String,
    val time: String,
    val message: String,
    val channelName: String,
    val topicName: String
)
