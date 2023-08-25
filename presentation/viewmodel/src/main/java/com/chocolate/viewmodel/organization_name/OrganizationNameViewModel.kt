package com.chocolate.viewmodel.organization_name

import androidx.lifecycle.viewModelScope
import com.chocolate.entities.exceptions.NoConnectionException
import com.chocolate.usecases.onboarding.ManageUserUsedAppUseCase
import com.chocolate.usecases.organization.SaveNameOrganizationUseCase
import com.chocolate.usecases.user.GetUserLoginStatusUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrganizationNameViewModel @Inject constructor(
    private val saveNameOrganizationsUseCase: SaveNameOrganizationUseCase,
    private val getUserLoginStatusUseCase: GetUserLoginStatusUseCase,
    private val stringsResource: StringsResource,
    private val manageUserUsedAppUseCase: ManageUserUsedAppUseCase
) : BaseViewModel<OrganizationNameUiState, OrganizationNameUiEffect>(OrganizationNameUiState()),
    OrganizationNameInteraction {

    init {
        getOnUserLoggedIn()
        getOnboardingStatus()
    }

    override fun onClickCreateOrganization() {
        sendUiEffect(OrganizationNameUiEffect.NavigateToCreateOrganization)
    }

    override fun onClickActionButton(organizationName: String) {
        _state.update { it.copy(isLoading = true) }
        tryToExecute({ saveNameOrganizationsUseCase(organizationName) }, ::onSuccess, ::onError)
    }

    private fun onSuccess(isCheck: Boolean) {
        _state.update { it.copy(isLoading = false, error = null) }
        if (isCheck) {
            sendUiEffect(OrganizationNameUiEffect.NavigateToLoginScreen)
        }
    }
    private fun getOnboardingStatus(){
        viewModelScope.launch {
            collectFlow(manageUserUsedAppUseCase.checkIfUserUsedAppOrNot()) {
                this.copy(onboardingState = it)
            }
        }
    }

    private fun onError(throwable: Throwable) {
        val errorMessage = when (throwable) {
            is NoConnectionException -> stringsResource.noConnectionMessage
            else -> stringsResource.organizationNameCannotBeEmpty
        }
        _state.update { it.copy(isLoading = false, error = errorMessage) }
    }

    private fun getOnUserLoggedIn(){
        viewModelScope.launch {
            viewModelScope.launch {
                collectFlow(getUserLoginStatusUseCase()) { this.copy(isLogged = it) }
            }
        }
    }
    override fun onOrganizationNameChange(organizationName: String) {
        _state.update { it.copy(organizationName = organizationName.trim(), isLoading = false) }
    }
}