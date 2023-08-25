package com.chocolate.viewmodel.welcome

import com.chocolate.viewmodel.base.BaseViewModel

data class WelcomeUiState(
    val image: Int = 0,
    val title: String = "",
    val buttonText: String = "",
    val onboardingState: Boolean = false,
) : BaseViewModel.BaseUiState