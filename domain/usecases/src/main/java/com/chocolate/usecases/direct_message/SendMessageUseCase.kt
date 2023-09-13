package com.chocolate.usecases.direct_message

import com.chocolate.entities.directMessage.DMMessage
import repositories.DirectMessageRepository
import java.util.Date
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val directMessageRepository: DirectMessageRepository
) {

    suspend operator fun invoke(
        message: String,
        senderId: String,
        sentAt: Date,
        currentChatId: String,
        currentOrgName: String,
        senderFullName: String,
        senderAvatarUrl: String,
    ) {
        val dmMessage = DMMessage(
            sentBy = senderId,
            messageText = message,
            sentAt = sentAt,
            senderFullName = senderFullName,
            senderAvatarUrl = senderAvatarUrl,
        )
        directMessageRepository.sendMessage(dmMessage, currentOrgName, currentChatId)
    }
}