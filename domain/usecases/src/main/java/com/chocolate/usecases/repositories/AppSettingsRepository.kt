package com.chocolate.usecases.repositories

import kotlinx.coroutines.flow.Flow

interface AppSettingsRepository {
    fun isDarkThemeEnabled(): Flow<Boolean>

    suspend fun setDarkTheme(isDarkTheme: Boolean)

    fun getLatestSelectedAppLanguage(): Flow<String>

    suspend fun updateAppLanguage(newLanguage: String): Boolean
}