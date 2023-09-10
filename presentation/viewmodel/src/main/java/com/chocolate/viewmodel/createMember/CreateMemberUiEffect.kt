package com.chocolate.viewmodel.createMember

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface CreateMemberUiEffect: BaseViewModel.BaseUiEffect {
    object ShowImagePicker: CreateMemberUiEffect
    object NavigateToLogin: CreateMemberUiEffect

}