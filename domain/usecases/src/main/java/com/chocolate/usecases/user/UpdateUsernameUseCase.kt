package com.chocolate.usecases.user

import com.chocolate.entities.user.Settings
import repositories.UsersRepositories
import javax.inject.Inject

class UpdateUsernameUseCase @Inject constructor(
    private val usersRepositories: UsersRepositories
){
    suspend operator fun invoke(settings: Settings){
        usersRepositories.updateSettings(settings)
    }
}