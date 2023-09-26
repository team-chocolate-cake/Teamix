package com.chocolate.viewmodel.choosemember

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface ChooseMemberUiEffect: BaseViewModel.BaseUiEffect {
    object NavigateToHome: ChooseMemberUiEffect
}