package com.chocolate.viewmodel.profile

data class ProfileUiState(
    val image: String = "",
    val name: String = "",
    val email: String = "",
    val message: String = "",
    val role:String = "",
    val languageMap: Map<String, String> = mapOf(
        LocalLanguage.Arabic.name to "ar",
        LocalLanguage.Chinese.name to "ae",
        LocalLanguage.Spanish.name to "es"
    ),
    val pagerNumber: Int = 0,
    var lastAppLanguage:String = "English",
    val showNoInternetLottie: Boolean = false,
    val showLanguageDialog: Boolean = false,
    val showThemeDialog: Boolean = false,
    val showClearHistoryDialog: Boolean = false,
    val showWarningDialog: Boolean = false,
    val showLogoutDialog: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)

data class OwnerPowerUiState(
    val showOrganizationNameSheet:Boolean=false,
    val showOrganizationImageSheet:Boolean=false,
    val showChangeMemberRoleDialog:Boolean=false,
    val showCreateChannelSheet:Boolean=false,
)


