package com.chocolate.viewmodel.conversation

interface ConversationInteraction {
    fun onMessageInputChanged(text: String)
    fun onSendMessage()
}