package com.chocolate.usecases.direct_message

import com.chocolate.entities.directMessage.DMMessage
import repositories.DirectMessageRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val directMessageRepository: DirectMessageRepository
) {

    suspend operator fun invoke(
        message: String,
        senderId: String,
        sentAt: String,
        currentChatId: String
    ) {
        val dmMessage = DMMessage(
            sentBy = senderId,
            messageText = message,
            sentAt = sentAt
        )
        directMessageRepository.sendMessage(dmMessage, currentChatId)
    }
}