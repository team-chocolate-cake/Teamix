package com.chocolate.viewmodel.organization_name

import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.onboarding.GetOnboardingStateUseCase
import com.chocolate.usecases.organization.SaveNameOrganizationUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrganizationNameViewModel @Inject constructor(
    private val saveNameOrganizationsUseCase: SaveNameOrganizationUseCase,
    private val getOnboardingStateUseCase: GetOnboardingStateUseCase
) : BaseViewModel<OrganizationNameUiState,OrganizationNameUiEffect>(OrganizationNameUiState()), OrganizationNameInteraction {

    init {
        getOnboardingState()
    }

    override fun onClickCreateOrganization() {
        sendUiEffect(OrganizationNameUiEffect.NavigateToCreateOrganization)
    }

    override fun onClickActionButton(organizationName: String) {
        viewModelScope.launch {
            saveNameOrganizationsUseCase(organizationName)
        }
        sendUiEffect(OrganizationNameUiEffect.NavigateToLoginScreen)
    }

    private fun getOnboardingState() {
        viewModelScope.launch {
            collectFlow(getOnboardingStateUseCase()) {
                this.copy(
                    onboardingState = it
                )
            }
        }
    }

    override fun onOrganizationNameChange(organizationName: String) {
        _state.update {
            it.copy(
                organizationName = organizationName.trim(),
                isLoading = false,
                error = null
            )
        }
    }
}