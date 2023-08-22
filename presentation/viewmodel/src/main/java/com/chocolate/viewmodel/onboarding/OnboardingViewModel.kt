package com.chocolate.viewmodel.onboarding

import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.onboarding.ManageOnboardingUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val manageOnboardingUseCase: ManageOnboardingUseCase
) : BaseViewModel<OnboardingUiState, OnboardingEffect>(OnboardingUiState()), OnboardingInteraction {
    override fun onClickLetsStart() {
        viewModelScope.launch { manageOnboardingUseCase.setOnboardingState(true) }
        sendUiEffect(OnboardingEffect.NavigateToOrganizationName)
    }
}