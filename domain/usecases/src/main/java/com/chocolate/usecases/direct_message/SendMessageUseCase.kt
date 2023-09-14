package com.chocolate.usecases.direct_message

import com.chocolate.entities.directMessage.MessageEntity
import repositories.DirectMessageRepository
import java.util.Date
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val directMessageRepository: DirectMessageRepository
) {

    suspend operator fun invoke(
        message: MessageEntity,
        currentChatId: String,
        currentOrgName: String,
    ) {
        directMessageRepository.sendMessage(message, currentOrgName, currentChatId)
    }
}