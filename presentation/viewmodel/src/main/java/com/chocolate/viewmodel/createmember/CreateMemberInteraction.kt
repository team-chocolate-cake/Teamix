package com.chocolate.viewmodel.createmember

import android.net.Uri

interface CreateMemberInteraction {
    fun onFullNameChange(name: String)

    fun onEmailChange(email: String)

    fun onPasswordChange(password: String)

    fun onErrorDismiss()

    fun onConfirmPasswordChange(password: String)

    fun onPasswordVisibilityChange(newPasswordVisibility:Boolean)

    fun onConfirmPasswordVisibilityChange(newPasswordVisibility:Boolean)

    fun onCreateButtonClick()

    fun onSignInClick()

    fun onPersonalImageChange(newUri: Uri)
}