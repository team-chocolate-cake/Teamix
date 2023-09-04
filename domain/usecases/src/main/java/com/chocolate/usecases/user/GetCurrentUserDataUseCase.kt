package com.chocolate.usecases.user

import repositories.UsersRepository
import javax.inject.Inject

class GetCurrentUserDataUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke() = usersRepository.getCurrentUser()

    suspend fun getRemoteCurrentUser() = usersRepository.getRemoteCurrentUser()
}

