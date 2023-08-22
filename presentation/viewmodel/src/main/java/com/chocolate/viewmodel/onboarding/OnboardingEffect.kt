package com.chocolate.viewmodel.onboarding

sealed interface OnboardingEffect {
    object NavigateToOrganizationName : OnboardingEffect
}