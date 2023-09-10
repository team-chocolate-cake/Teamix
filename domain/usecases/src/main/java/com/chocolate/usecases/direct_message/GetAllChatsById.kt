package com.chocolate.usecases.direct_message

import com.chocolate.entities.directMessage.Chat
import com.chocolate.entities.directMessage.DMMessage
import repositories.DirectMessageRepository
import javax.inject.Inject

class GetAllChatsById @Inject constructor(
    private val directMessageRepository: DirectMessageRepository
) {

    suspend operator fun invoke(
        userId: String
    ) : List<Chat> {
        return directMessageRepository.getChatsByUserId(userId)
    }
}