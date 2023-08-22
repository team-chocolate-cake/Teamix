package com.chocolate.usecases.user

import com.chocolate.entities.exceptions.EmptyEmailException
import com.chocolate.entities.exceptions.EmptyFullNameException
import com.chocolate.entities.exceptions.SameUserDataException
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
        newUserInformationSettings: User
    ): Boolean {
        if ((oldUserInformation.email == newUserInformationSettings.email) &&
            (oldUserInformation.fullName == newUserInformationSettings.fullName)) {
            throw SameUserDataException
        } else if (newUserInformationSettings.email.isBlank()) {
            throw EmptyEmailException
        } else if (newUserInformationSettings.fullName.isBlank()) {
            throw EmptyFullNameException
        }
        return true
    }
}