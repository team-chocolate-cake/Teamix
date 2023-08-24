package com.chocolate.viewmodel.profile

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface ProfileEffect: BaseViewModel.BaseUiEffect{
    //not finished yet
    //object NavigateToOwnerPower: ProfileEffect
    object NavigateToOrganizationScreen: ProfileEffect
    object NavigateToSearchScreen:ProfileEffect
}