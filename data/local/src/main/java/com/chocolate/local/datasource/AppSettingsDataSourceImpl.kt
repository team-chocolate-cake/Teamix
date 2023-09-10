package com.chocolate.local.datasource

import com.chocolate.repository.datastore.local.AppSettingsDataSource
import javax.inject.Inject

class AppSettingsDataSourceImpl @Inject constructor(

): AppSettingsDataSource {
    override suspend fun isDarkThemeEnabled() {
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