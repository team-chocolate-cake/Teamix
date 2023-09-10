package com.chocolate.repository.repository

import repositories.AppSettingsRepository
import javax.inject.Inject

class AppSettingsRepositoryImpl @Inject constructor(): AppSettingsRepository {
    override suspend fun isDarkThemeEnabled(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updateDarkTheme(isDarkTheme: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun getLatestSelectedAppLanguage() {
        TODO("Not yet implemented")
    }

    override suspend fun updateAppLanguage(newLanguage: String): Boolean {
        TODO("Not yet implemented")
    }
}