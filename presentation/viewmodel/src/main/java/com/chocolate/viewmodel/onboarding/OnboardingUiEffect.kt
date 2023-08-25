package com.chocolate.viewmodel.onboarding

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface OnboardingUiEffect : BaseViewModel.BaseUiEffect {
    object NavigateToOrganizationName : OnboardingUiEffect
}