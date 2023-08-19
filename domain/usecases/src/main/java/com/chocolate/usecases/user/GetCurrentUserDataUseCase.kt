package com.chocolate.usecases.user

import repositories.UsersRepository
import javax.inject.Inject

class GetCurrentUserDataUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke() =
        usersRepository.getLocalCurrentUser()
            .takeIf { it != null }
            ?: usersRepository.getRemoteCurrentUser()
                .also { usersRepository.upsertCurrentUser(it) }
}