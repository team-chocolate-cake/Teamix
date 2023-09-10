package com.chocolate.usecases.direct_message

import com.chocolate.entities.directMessage.Chat
import repositories.DirectMessageRepository
import javax.inject.Inject

class GetAllChatsByIdUseCase @Inject constructor(
    private val directMessageRepository: DirectMessageRepository
) {

    suspend operator fun invoke(
        userId: String
    ) : List<Chat> {
        return directMessageRepository.getChatsByUserId(userId)
    }
}