package com.chocolate.viewmodel.createmember

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface CreateMemberUiEffect: BaseViewModel.BaseUiEffect {
    object ShowImagePicker: CreateMemberUiEffect
    object NavigateToLogin: CreateMemberUiEffect
    object NavigateToHome: CreateMemberUiEffect

}