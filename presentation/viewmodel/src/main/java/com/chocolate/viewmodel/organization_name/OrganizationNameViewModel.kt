package com.chocolate.viewmodel.organization_name

import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.onboarding.ShouldShowOnboardingScreenUseCase
import com.chocolate.usecases.organization.SaveNameOrganizationUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrganizationNameViewModel @Inject constructor(
    private val saveNameOrganizationsUseCase: SaveNameOrganizationUseCase,
    private val shouldShowOnboardingScreenUseCase: ShouldShowOnboardingScreenUseCase
) : BaseViewModel<OrganizationNameUiState,OrganizationNameUiEffect>(OrganizationNameUiState()), OrganizationNameInteraction {

    init {
        checkOnboradingState()
    }

    fun saveNameOrganization(nameOrganization: String) {
        viewModelScope.launch {
            saveNameOrganizationsUseCase(nameOrganization)
        }
    }

    private fun checkOnboradingState(){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isShowOnBorading = shouldShowOnboardingScreenUseCase()
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

    override fun onClickCreateNewOrganization() {
        // Open web view to create new organization
    }
}