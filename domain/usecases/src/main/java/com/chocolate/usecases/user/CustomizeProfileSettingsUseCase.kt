package com.chocolate.usecases.user

import com.chocolate.entities.exceptions.NullDataException
import com.chocolate.entities.exceptions.TeamixException
import com.chocolate.entities.uills.Empty
import repositories.UsersRepository
import javax.inject.Inject

class CustomizeProfileSettingsUseCase @Inject constructor(
    private val usersRepositories: UsersRepository
) {
    suspend fun saveNewSelectedLanguage(newLanguage: String){
        if(newLanguage.isBlank()){
            throw NullDataException(String.Empty)
        }
        val isUpdateSuccessful = usersRepositories.updateAppLanguage(newLanguage)
        if (!isUpdateSuccessful){
            throw TeamixException(String.Empty)
        }
    }

    suspend fun getLastSelectedAppLanguage() = usersRepositories.getLastSelectedAppLanguage()

    suspend fun updateDarkTheme(isDarkTheme:Boolean){
        usersRepositories.updateDarkTheme(isDarkTheme)
    }

    suspend fun isDarkThem()=usersRepositories.isDarkThemeEnabled()

}