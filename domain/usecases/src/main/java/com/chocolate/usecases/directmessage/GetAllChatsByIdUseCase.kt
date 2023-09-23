package com.chocolate.usecases.directmessage

import com.chocolate.entities.directMessage.Chat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import repositories.DirectMessageRepository
import javax.inject.Inject

class GetAllChatsByIdUseCase @Inject constructor(
    private val directMessageRepository: DirectMessageRepository
) {

    suspend operator fun invoke(
        memberId: String,
    ): Flow<List<Chat>> {
        return directMessageRepository.getChatsByUserId(memberId)
            .map { it.reversed() }
    }
}