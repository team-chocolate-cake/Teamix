package com.chocolate.usecases.member

import com.chocolate.entities.exceptions.NullDataException
import com.chocolate.entities.exceptions.TeamixException
import com.chocolate.entities.uills.Empty
import repositories.AppSettingsRepository
import javax.inject.Inject

class CustomizeProfileSettingsUseCase @Inject constructor(
    private val appSettingsRepository: AppSettingsRepository
) {
    suspend fun updateAppLanguage(newLanguage: String) {
        if (newLanguage.isBlank()) {
            throw NullDataException(null)
        }
        val isUpdateSuccessful = appSettingsRepository.updateAppLanguage(newLanguage)
        if (!isUpdateSuccessful) {
            throw TeamixException(String.Empty)
        }
    }

    suspend fun getLatestSelectedAppLanguage() =
        appSettingsRepository.getLatestSelectedAppLanguage()

    suspend fun updateDarkTheme(isDarkTheme: Boolean) {
        appSettingsRepository.updateDarkTheme(isDarkTheme)
    }

    suspend fun isDarkThemeEnabled(): Boolean = appSettingsRepository.isDarkThemeEnabled()

}