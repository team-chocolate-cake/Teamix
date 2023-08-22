package com.chocolate.viewmodel.onboarding

import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.onboarding.SetOnboardingUseCase
import com.chocolate.viewmodel.R
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val setOnboardingUseCase: SetOnboardingUseCase
) : BaseViewModel<OnboardingUiState, OnboardingEffect>(OnboardingUiState()), OnboardingInteraction {
    override fun onClickLetsStart() {
        viewModelScope.launch { setOnboardingUseCase(true) }
        sendUiEffect(OnboardingEffect.NavigateToOrganizationName)
    }
}