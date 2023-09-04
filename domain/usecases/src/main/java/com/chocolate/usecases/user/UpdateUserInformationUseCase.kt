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

    suspend fun updateUsername(username: String, id: Int, role: Int): String {
        if (isValidUsername(username)) {
            usersRepository.updateUserById(id, username, role)
            return username
        } else {
            throw ValidationException(INVALID_USERNAME_ERROR_CODE)
        }
    }

    private fun isValidUsername(username: String): Boolean {
        val pattern = Regex("^[a-zA-Z][a-zA-Z0-9_ ]{2,}$")
        return pattern.matches(username)
    }

    private fun validNewUserInformation(
        oldUserInformation: User,
        newUserInformation: User
    ): Boolean {
        if ((oldUserInformation.email == newUserInformation.email) &&
            (oldUserInformation.fullName == newUserInformation.fullName)
        ) {
            throw ValidationException("100")
        } else if (newUserInformation.email.isBlank()) {
            throw ValidationException("200")
        } else if (newUserInformation.fullName.isBlank()) {
            throw ValidationException("300")
        }
        return true
    }

    companion object{
        private const val INVALID_USERNAME_ERROR_CODE = "400"
    }
}