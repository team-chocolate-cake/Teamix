package com.chocolate.usecases.user

import kotlinx.coroutines.flow.Flow
import repositories.UsersRepositories
import javax.inject.Inject

class GetUserLoginStatusUseCase @Inject constructor(
    private val usersRepositories: UsersRepositories
) {
    suspend operator fun invoke(): Flow<Boolean> {
        return usersRepositories.getUserLoginState()
    }
}