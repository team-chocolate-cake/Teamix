package com.chocolate.usecases.direct_message

import com.chocolate.entities.directMessage.ChatEntity
import kotlinx.coroutines.flow.Flow
import repositories.DirectMessageRepository
import javax.inject.Inject

class GetAllChatsByIdUseCase @Inject constructor(
    private val directMessageRepository: DirectMessageRepository
) {

    suspend operator fun invoke(
        memberId: String,
        currentOrganizationName:String
    ) :Flow<List<ChatEntity>> {
        return directMessageRepository.getChatsByUserId(memberId , currentOrganizationName)
    }
}