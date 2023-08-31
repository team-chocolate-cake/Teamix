package com.chocolate.viewmodel.profile

import android.content.Context

interface ProfileInteraction {
    fun updateEditUsernameDialogState(editUsernameState:Boolean)

    fun onUpdateLanguage(language: String)
    fun onUpdateLanguageDialogState(showDialog: Boolean)
    fun onUpdateLogoutDialogState(showDialog: Boolean)
    fun onLogoutButtonClicked()
    fun onUsernameChange(username: String)
    fun onUserInformationFocusChange()
    fun onClickRetryToUpdatePersonalInformation()
    fun onClickRetryToGetPersonalInformation()
    fun areUserDataEqual(): Boolean
    fun onConfirmChange()
    fun onClickProfileButton()
    fun onClickSettingsButton()
    fun onClickChangeMemberRole()
    fun onClickDarkThemeSwitch(darkTheme: Boolean,context:Context)
    fun restartActivity(context:Context)

}