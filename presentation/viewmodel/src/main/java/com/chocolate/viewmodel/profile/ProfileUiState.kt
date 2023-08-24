package com.chocolate.viewmodel.profile

import com.chocolate.viewmodel.base.BaseViewModel

data class ProfileUiState(
    val imageUrl: String = "",
    val name: String = "",
    val email: String = "",
    val message: String? = null,
    val role:String = "",
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
    val newUsername: String = "",
    val newEmail: String = "",
    val originalName: String = "",
    val originalEmail: String = "",
    val isLoading: Boolean = true,
    val error: String? = null
): BaseViewModel.BaseUiState


