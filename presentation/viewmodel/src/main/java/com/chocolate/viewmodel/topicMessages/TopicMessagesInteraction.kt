package com.chocolate.viewmodel.topicMessages


interface TopicMessagesInteraction {
    fun onClickBackButton()
    fun onMessageInputChanged(text: String)
    fun onSendMessage(text: String)
    fun onSaveMessage(message: MessageUiState)

}