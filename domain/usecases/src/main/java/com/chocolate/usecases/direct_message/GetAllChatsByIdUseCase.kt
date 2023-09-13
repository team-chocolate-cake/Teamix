package com.chocolate.usecases.direct_message

import com.chocolate.entities.directMessage.DMChat
import kotlinx.coroutines.flow.Flow
import repositories.DirectMessageRepository
import javax.inject.Inject

class GetAllChatsByIdUseCase @Inject constructor(
    private val directMessageRepository: DirectMessageRepository
) {

    suspend operator fun invoke(
        userId: String,
        currentOrgName:String
    ) :Flow<List<DMChat>> {
        return directMessageRepository.getChatsByUserId(userId , currentOrgName)
    }
}