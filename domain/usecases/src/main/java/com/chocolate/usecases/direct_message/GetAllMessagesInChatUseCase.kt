package com.chocolate.usecases.direct_message

import com.chocolate.entities.directMessage.DMMessage
import kotlinx.coroutines.flow.Flow
import repositories.DirectMessageRepository
import javax.inject.Inject

class GetAllMessagesInChatUseCase @Inject constructor(
    private val directMessageRepository: DirectMessageRepository
) {

    suspend operator fun invoke(
        groupId: String,
        currentOrgName:String
    ) : Flow<List<DMMessage>> {
        return directMessageRepository.fetchMessagesByGroupId(groupId , currentOrgName)
    }
}