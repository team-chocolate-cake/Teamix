package com.chocolate.usecases.directmessage

import repositories.DirectMessageRepository
import javax.inject.Inject

class CreateNewChatUseCase @Inject constructor(
    private val directMessageRepository: DirectMessageRepository
) {

    suspend operator fun invoke(
        userId: List<String>,
    ) : String {
        return directMessageRepository.createGroup(userId)
    }
}