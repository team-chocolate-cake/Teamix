package com.chocolate.viewmodel.profile

interface ProfileInteraction {
    fun updateLanguageDialogState(showDialog:Boolean)
    fun updateThemeDialogState(showDialog:Boolean)
    fun updateClearHistoryState(showDialog:Boolean)
    fun updateLogoutDialogState(showDialog:Boolean)
    fun updateWarningDialog(showDialog: Boolean)
    fun onLogoutButtonClicked()
    fun onUsernameChange(username: String)
    fun onEmailChange(email: String)
    fun onUserInformationFocusChange()
    fun onClickRetryToUpdatePersonalInformation()
    fun onClickRetryToGetPersonalInformation()
    fun areUserDataEqual(): Boolean
    fun onRevertChange()
    fun onClickProfileButton()
    fun onClickSettingsButton()
    fun onClickChangeMemberRole()
}