package com.chocolate.viewmodel.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.onboarding.SetOnboardingScreenShownUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val setOnboardingScreenShownUseCase: SetOnboardingScreenShownUseCase
): ViewModel(), OnboardingEffect {
    override fun setOnboardingShown() {
        viewModelScope.launch {
            setOnboardingScreenShownUseCase()
        }
    }

}