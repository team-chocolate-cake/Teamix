package com.chocolate.usecases.user

import com.chocolate.entities.exceptions.ValidationException
import com.chocolate.entities.user.User
import repositories.UsersRepository
import javax.inject.Inject

class UpdateUserInformationUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
    private val getCurrentUserDataUseCase: GetCurrentUserDataUseCase
) {
    suspend operator fun invoke(user: User) {
        val oldUserInformation = getCurrentUserDataUseCase()
        user.takeIf { newUserInformation ->
            validNewUserInformation(oldUserInformation, newUserInformation)
        }?.run {
            usersRepository.updateSettings(user).also {
                usersRepository.upsertCurrentUser(user.email)
            }
        }
    }

    private fun validNewUserInformation(
        oldUserInformation: User,
        newUserInformation: User
    ): Boolean {
        if ((oldUserInformation.email == newUserInformation.email) &&
            (oldUserInformation.fullName == newUserInformation.fullName)) {
            throw ValidationException("The same data")
        } else if (newUserInformation.email.isBlank()) {
            throw ValidationException("The email shouldn't be empty")
        } else if (newUserInformation.fullName.isBlank()) {
            throw ValidationException("The full name shouldn't be empty")
        }
        return true
    }
}