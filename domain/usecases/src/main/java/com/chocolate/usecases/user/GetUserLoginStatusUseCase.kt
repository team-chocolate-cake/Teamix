package com.chocolate.usecases.user

import kotlinx.coroutines.flow.Flow
import repositories.UsersRepository
import javax.inject.Inject

class GetUserLoginStatusUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(): Flow<Boolean> {
        return usersRepository.getUserLoginState()
    }
}