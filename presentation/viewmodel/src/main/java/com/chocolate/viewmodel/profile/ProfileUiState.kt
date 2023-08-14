package com.chocolate.viewmodel.profile

data class ProfileUiState(
    val image:String="",
    val name:String="name",
    val job:String="job",
    val team:String="team",
    val number:String="number",
    val status:String="status",
    val showLanguageDialog:Boolean=false,
    val showThemeDialog:Boolean=false,
    val showClearHistoryDialog:Boolean=false,
    val showLogoutDialog:Boolean=false,
    val isLoading:Boolean=false,
    val error:List<String>?=null
)

data class OwnerPowerUiState(
    val showOrganizationNameSheet:Boolean=false,
    val showOrganizationImageSheet:Boolean=false,
    val showChangeMemberRoleDialog:Boolean=false,
    val showCreateChannelSheet:Boolean=false,
)


