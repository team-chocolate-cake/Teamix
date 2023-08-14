package com.chocolate.viewmodel.profile

sealed interface ProfileEffect{
    object NavigateToOwnerPower: ProfileEffect
    object NavigateToLoginScreen: ProfileEffect
}