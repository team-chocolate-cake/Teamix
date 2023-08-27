package com.chocolate.usecases.direct_messages

import repositories.MessagesRepository
import java.io.File
import javax.inject.Inject

class ManageMessagesUseCase @Inject constructor(
    private val messagesRepository: MessagesRepository
) {

    suspend fun sendDirectMessage(type: String, recipients: String, content: String): Boolean {
        return messagesRepository.sendDirectMessage(type, recipients, content) > 0
    }

    suspend fun editMessage(messageId: Int, content: String, topic: String = "") {
        messagesRepository.editMessage(messageId, content, topic)
    }

    suspend fun deleteMessage(messageId: Int) {
        messagesRepository.deleteMessage(messageId)
    }

    suspend fun uploadFile(file: File): String? {
        return messagesRepository.uploadFile(file)
    }
}