package com.chocolate.viewmodel.login

import androidx.lifecycle.SavedStateHandle

class LoginArgs(savedStateHandle: SavedStateHandle) {
    val organizationName: String = checkNotNull(savedStateHandle[ORGANIZATION_NAME])

    companion object {
        const val ORGANIZATION_NAME = "organizationName"
    }
}