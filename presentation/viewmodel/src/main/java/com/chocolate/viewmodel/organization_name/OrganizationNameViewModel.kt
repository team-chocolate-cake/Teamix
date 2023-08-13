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

    fun saveNameOrganization(nameOrganization: String) {
        viewModelScope.launch {
            saveNameOrganizationsUseCase(nameOrganization)
        }
    }

    private fun getOnboardingState(){
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