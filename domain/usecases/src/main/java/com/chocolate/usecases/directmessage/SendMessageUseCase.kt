package com.chocolate.usecases.directmessage

import com.chocolate.entities.messages.Message
import repositories.DirectMessageRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val directMessageRepository: DirectMessageRepository
) {

    suspend operator fun invoke(
        message: Message,
        currentChatId: String,
    ) {
        directMessageRepository.sendMessage(message, currentChatId)
    }
}