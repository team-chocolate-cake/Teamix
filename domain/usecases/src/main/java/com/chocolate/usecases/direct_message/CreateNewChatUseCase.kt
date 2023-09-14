package com.chocolate.usecases.direct_message

import repositories.DirectMessageRepository
import javax.inject.Inject

class CreateNewChatUseCase @Inject constructor(
    private val directMessageRepository: DirectMessageRepository
) {

    suspend operator fun invoke(
        userId: List<String>,
        currentOrgName:String
    ) : String {
        return directMessageRepository.createGroup(userId , currentOrgName)
    }
}