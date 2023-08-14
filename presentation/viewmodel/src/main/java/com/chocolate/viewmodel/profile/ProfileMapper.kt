package com.chocolate.viewmodel.profile

import com.chocolate.entities.user.OwnerUser
import com.chocolate.entities.user.Settings

fun OwnerUser.toOwnerUserUiState(): ProfileUiState{
    return ProfileUiState(
        image = this.avatarUrl,
        name = this.fullName
    )
}

fun Settings.toSettingsUiState(): ProfileUiState{
    return ProfileUiState(name = this.fullName)
}