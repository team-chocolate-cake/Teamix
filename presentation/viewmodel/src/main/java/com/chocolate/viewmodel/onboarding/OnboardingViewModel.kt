package com.chocolate.viewmodel.onboarding

import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.onboarding.ManageUserUsedAppUseCase
import com.chocolate.usecases.user.CustomizeProfileSettingsUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val manageUserUsedApp: ManageUserUsedAppUseCase,
    private val settings: CustomizeProfileSettingsUseCase,
) : BaseViewModel<OnboardingUiState, OnboardingUiEffect>(OnboardingUiState()),
    OnboardingInteraction {
    init {
        isDarkTheme()
    }

    private fun isDarkTheme() {
        viewModelScope.launch {
            _state.update { it.copy(isDarkTheme = settings.isDarkThem()) }
        }
    }

    override fun onClickLetsStart() {
        viewModelScope.launch {
            manageUserUsedApp.setUserUsedAppForFirstTime(true)
            sendUiEffect(OnboardingUiEffect.NavigateToOrganizationName)
        }
    }
}