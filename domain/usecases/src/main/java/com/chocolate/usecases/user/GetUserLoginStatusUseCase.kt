package com.chocolate.usecases.user

import kotlinx.coroutines.flow.Flow
import repositories.UsersRepository
import javax.inject.Inject

class GetUserLoginStatusUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(): Boolean {
        return usersRepository.getUserLoginState()
    }
}