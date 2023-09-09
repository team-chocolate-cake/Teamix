package repositories

interface AppSettingsRepository {

    suspend fun isDarkThemeEnabled(): Boolean
    suspend fun updateDarkTheme(isDarkTheme: Boolean)
    suspend fun getLatestSelectedAppLanguage()
    suspend fun updateAppLanguage(newLanguage: String): Boolean

}