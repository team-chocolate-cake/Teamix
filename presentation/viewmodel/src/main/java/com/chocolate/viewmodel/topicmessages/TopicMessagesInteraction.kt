package com.chocolate.viewmodel.topicmessages


interface TopicMessagesInteraction {
    fun onMessageInputChanged(text: String)
    fun onSendMessage(text: String)
    fun onSaveMessage(message: MessageUiState)
}