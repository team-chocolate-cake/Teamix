package com.chocolate.viewmodel.onboarding

import com.chocolate.viewmodel.base.BaseViewModel

data class OnboardingUiState(
    val image: Int = 0,
    val title: String = "",
    val description: String = "",
    val buttonText: String = "",
    val onboardingState: Boolean = false,
    val isLastPage: Boolean = false
) : BaseViewModel.BaseUiState