package com.chocolate.repository.repository

import com.chocolate.repository.datasource.local.PreferencesDataSource
import kotlinx.coroutines.flow.Flow
import repositories.AppSettingsRepository
import javax.inject.Inject

class AppSettingsRepositoryImpl @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource
): AppSettingsRepository {
    override fun isDarkThemeEnabled(): Flow<Boolean> =
        preferencesDataSource.isDarkThemeEnabled()


    override suspend fun setDarkTheme(isDarkTheme: Boolean) {
        preferencesDataSource.setDarkTheme(isDarkTheme)
    }

    override fun getLatestSelectedAppLanguage(): Flow<String> =
        preferencesDataSource.getLatestSelectedAppLanguage()

    override suspend fun updateAppLanguage(newLanguage: String): Boolean {
        return preferencesDataSource.updateAppLanguage(newLanguage)
    }
}