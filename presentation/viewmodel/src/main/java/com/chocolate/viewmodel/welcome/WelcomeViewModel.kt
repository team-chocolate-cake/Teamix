package com.chocolate.viewmodel.welcome

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.onboarding.ManageUserUsedAppUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val manageUserUsedAppUseCase: ManageUserUsedAppUseCase,
) : BaseViewModel<WelcomeUiState, Unit>(WelcomeUiState()){
    init {
        getOnUserUsedAppForFirstTime()
    }
    private fun getOnUserUsedAppForFirstTime() {
        viewModelScope.launch {
            collectFlow(manageUserUsedAppUseCase.checkIfUserUsedAppOrNot()) {
                this.copy(onboardingState = it)
            }
        }
    }
}