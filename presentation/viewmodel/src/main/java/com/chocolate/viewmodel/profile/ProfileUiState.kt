package com.chocolate.viewmodel.profile

data class ProfileUiState(
    val image:String="",
    val name:String="name",
    val job:String="job",
    val team:String="team",
    val number:String="number",
    val status:String="status",
    val isLoading:Boolean=false,
    val error:List<String>?=null
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


