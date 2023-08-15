package com.chocolate.viewmodel.profile

data class ProfileUiState(
    val image: String = "",
    val name: String = "name",
    val email: String = "",
    val message: String = "",
    val role:String = "",
    val showLanguageDialog: Boolean = false,
    val showThemeDialog: Boolean = false,
    val showClearHistoryDialog: Boolean = false,
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


