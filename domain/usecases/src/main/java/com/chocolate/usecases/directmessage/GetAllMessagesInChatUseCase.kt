package com.chocolate.usecases.directmessage

import com.chocolate.entities.messages.Message
import kotlinx.coroutines.flow.Flow
import repositories.DirectMessageRepository
import javax.inject.Inject

class GetAllMessagesInChatUseCase @Inject constructor(
    private val directMessageRepository: DirectMessageRepository
) {

    suspend operator fun invoke(
        groupId: String,
    ) : Flow<List<Message>> {
        return directMessageRepository.fetchMessagesByGroupId(groupId)
    }
}