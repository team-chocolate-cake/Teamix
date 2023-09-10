package com.chocolate.usecases.direct_message

import com.chocolate.entities.directMessage.Chat
import com.chocolate.entities.directMessage.DMMessage
import repositories.DirectMessageRepository
import javax.inject.Inject

class GetAllMessagesInChatUseCase @Inject constructor(
    private val directMessageRepository: DirectMessageRepository
) {

    suspend operator fun invoke(
        groupId: String
    ) : List<DMMessage> {
        return directMessageRepository.fetchMessagesByGroupId(groupId)
    }
}