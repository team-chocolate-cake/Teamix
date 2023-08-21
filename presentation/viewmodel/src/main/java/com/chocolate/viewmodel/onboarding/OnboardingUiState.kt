package com.chocolate.viewmodel.onboarding

data class OnboardingUiState(
    val image: Int,
    val title: String,
    val description: String,
    val buttonText: String,
    val isLastPage: Boolean = false
)