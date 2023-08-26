package com.chocolate.viewmodel.profile

import org.intellij.lang.annotations.Language

interface ProfileInteraction {
    fun onUpdateLanguage(language: String)
    fun onUpdateLanguageDialogState(showDialog: Boolean)
    fun onUpdateLogoutDialogState(showDialog: Boolean)
    fun onUpdateWarningDialog(showDialog: Boolean)
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