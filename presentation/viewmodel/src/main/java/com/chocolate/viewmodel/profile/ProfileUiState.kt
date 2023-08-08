package com.chocolate.viewmodel.profile

data class ProfileUiState(
    val image:String,
    val name:String,
    val job:String,
    val team:String,
    val number:String,
    val status:String,
)
data class SettingUiState(
    val showLanguageDialog:Boolean,
    val showThemeColorDialog:Boolean,
    val showClearHistoryDialog:Boolean,
    val showLogoutDialog:Boolean,
)
data class OwnerPowerUiState(
    val showOrganizationNameSheet:Boolean,
    val showOrganizationImageSheet:Boolean,
    val showChangeMemberRoleSheet:Boolean,
    val showCreateChannelSheet:Boolean,
)


