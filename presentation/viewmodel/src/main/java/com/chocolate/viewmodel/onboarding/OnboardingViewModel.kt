package com.chocolate.viewmodel.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.onboarding.SetOnboardingStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val setOnboardingStateUseCase: SetOnboardingStateUseCase
): ViewModel(), OnboardingEffect {
    override fun setOnboardingShown() {
        viewModelScope.launch {
            setOnboardingStateUseCase(true)
        }
    }
}