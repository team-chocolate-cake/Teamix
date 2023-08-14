package com.chocolate.viewmodel.profile

interface ProfileInteraction {
    fun updateLanguageDialogState(showDialog:Boolean)
    fun updateThemeDialogState(showDialog:Boolean)
    fun updateClearHistoryState(showDialog:Boolean)
    fun updateLogoutDialogState(showDialog:Boolean)
    fun onClickOwnerPower()
}