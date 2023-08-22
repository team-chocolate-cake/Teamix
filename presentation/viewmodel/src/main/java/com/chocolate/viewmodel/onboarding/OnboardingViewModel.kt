package com.chocolate.viewmodel.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.onboarding.SetOnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val setOnboardingUseCase: SetOnboardingUseCase
): ViewModel(), OnboardingEffect {
    override fun onClickNext() {
        viewModelScope.launch {
            setOnboardingUseCase(true)
        }
    }
}