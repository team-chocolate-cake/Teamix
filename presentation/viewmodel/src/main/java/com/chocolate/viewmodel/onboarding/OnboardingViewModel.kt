package com.chocolate.viewmodel.onboarding

import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.onboarding.ManageUserUsedAppUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val manageUserUsedAppUseCase: ManageUserUsedAppUseCase
) : BaseViewModel<OnboardingUiState, Unit>(OnboardingUiState()) {
    fun onClickLetsStart() {
        viewModelScope.launch { manageUserUsedAppUseCase.setUserUsedAppForFirstTime(true) }
    }
}