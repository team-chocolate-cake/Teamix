package com.chocolate.repository.datastore.local

interface AppSettingsDataSource {
    suspend fun isDarkThemeEnabled()
    suspend fun updateDarkTheme(isDarkTheme: Boolean)
    suspend fun getLatestSelectedAppLanguage()
    suspend fun updateAppLanguage(newLanguage: String): Boolean
}