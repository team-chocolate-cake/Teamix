package com.chocolate.presentation.screens.topic_details

data class TopicScreenUiState(
    val photoAndVideo: List<PhotoOrVideoUiState> = emptyList()
)

data class PhotoOrVideoUiState(
    var isSelected:Boolean,
    val file:String
)
