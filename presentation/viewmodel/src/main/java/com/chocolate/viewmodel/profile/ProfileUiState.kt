package com.chocolate.viewmodel.profile

import com.chocolate.entities.uills.Empty

data class ProfileUiState(
    val imageUrl: String = String.Empty,
    val name: String = String.Empty,
    val email: String = String.Empty,
    val message: String? = null,
    val role: String = String.Empty,
    val languageMap: Map<String, String> = mapOf(
        LocalLanguage.English.name to "en",
        LocalLanguage.Arabic.name to "ar",
        LocalLanguage.Chinese.name to "ae",
        LocalLanguage.Spanish.name to "es"
    ),
    val pagerNumber: Int = 0,
    var lastAppLanguage: String = "English",
    val showNoInternetLottie: Boolean = false,
    val showLanguageDialog: Boolean = false,
    val showWarningDialog: Boolean = false,
    val showLogoutDialog: Boolean = false,
    val newUsername: String = String.Empty,
    val newEmail: String = String.Empty,
    val originalName: String = String.Empty,
    val originalEmail: String = String.Empty,
    val isDarkTheme: Boolean = false,
    val isLoading: Boolean = true,
    val error: String? = null
)


