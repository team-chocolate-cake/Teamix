package com.chocolate.usecases.usecase.member

import com.chocolate.entities.util.NullDataException
import com.chocolate.entities.util.TeamixException
import com.chocolate.entities.util.Empty
import kotlinx.coroutines.flow.Flow
import com.chocolate.usecases.repositories.AppSettingsRepository
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

    fun getLatestSelectedAppLanguage() =
        appSettingsRepository.getLatestSelectedAppLanguage()

    suspend fun setAppThemeToDark(isDarkTheme: Boolean) {
        appSettingsRepository.setDarkTheme(isDarkTheme)
    }

    fun isDarkThemeEnabled(): Flow<Boolean> = appSettingsRepository.isDarkThemeEnabled()

}