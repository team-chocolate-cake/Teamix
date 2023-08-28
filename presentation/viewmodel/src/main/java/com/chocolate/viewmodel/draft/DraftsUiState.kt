package com.chocolate.viewmodel.draft

import java.text.SimpleDateFormat
import java.util.Locale

data class DraftsUiState(
    val draftItems: List<DraftItemUiState> = emptyList(),
    val showNoInternetLottie: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null

)

data class DraftItemUiState(
    val id: Int = 0,
    val topicName: String = "",
    val messageContent: String = "",
    val time: String = "",
    val isInStream: Boolean
){

    val formattedTime: String
        get() {
            val inputFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'Z yyyy", Locale.ENGLISH)
            val outputFormat = SimpleDateFormat("yyyy/MM/dd hh:mm a", Locale.getDefault())
            val date = inputFormat.parse(time)
            return outputFormat.format(date)
        }
}