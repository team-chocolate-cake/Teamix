package com.chocolate.usecases.directmessage

import com.chocolate.entities.directMessage.DirectMessage
import kotlinx.coroutines.flow.Flow
import repositories.DirectMessageRepository
import javax.inject.Inject

class GetAllMessagesInChatUseCase @Inject constructor(
    private val directMessageRepository: DirectMessageRepository
) {

    suspend operator fun invoke(
        groupId: String,
        currentOrgName:String
    ) : Flow<List<DirectMessage>> {
        return directMessageRepository.fetchMessagesByGroupId(groupId , currentOrgName)
    }
}