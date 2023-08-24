package com.chocolate.viewmodel.profile

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface ProfileEffect: BaseViewModel.BaseUiEffect{
    object NavigateToOrganizationScreen: ProfileEffect

}