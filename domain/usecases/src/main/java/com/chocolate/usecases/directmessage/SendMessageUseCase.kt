package com.chocolate.usecases.directmessage

import com.chocolate.entities.directMessage.DirectMessage
import repositories.DirectMessageRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val directMessageRepository: DirectMessageRepository
) {

    suspend operator fun invoke(
        directMessage: DirectMessage,
        currentChatId: String,
        currentOrgName: String,
    ) {
        directMessageRepository.sendMessage(directMessage, currentOrgName, currentChatId)
    }
}