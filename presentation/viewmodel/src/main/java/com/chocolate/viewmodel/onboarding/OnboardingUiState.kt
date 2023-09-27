package com.chocolate.viewmodel.onboarding

import com.chocolate.entities.util.Empty

data class OnboardingUiState(
    val image: Int = 0,
    val title: String = String.Empty,
    val description: String = String.Empty,
    val buttonText: String = String.Empty,
    val onboardingState: Boolean = false,
    val isLastPage: Boolean = false,
    val isDarkTheme: Boolean = false,
)