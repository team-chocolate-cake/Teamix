package com.chocolate.viewmodel.organization_name

import android.util.Log
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
    private val manageUserUsedAppUseCase: ManageUserUsedAppUseCase,
    private val getUserLoginStatusUseCase: GetUserLoginStatusUseCase,
    private val stringsResource: StringsResource
) : BaseViewModel<OrganizationNameUiState, OrganizationNameUiEffect>(OrganizationNameUiState()),
    OrganizationNameInteraction {

    init {
        getOnUserUsedAppForFirstTime()

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

    private fun onError(throwable: Throwable) {
        val errorMessage = when (throwable) {
            is NoConnectionException -> stringsResource.noConnectionMessage
            else -> stringsResource.organizationNameCannotBeEmpty
        }
        _state.update { it.copy(isLoading = false, error = errorMessage) }
    }

    private fun getOnUserUsedAppForFirstTime() {
        viewModelScope.launch {
            collectFlow(manageUserUsedAppUseCase.checkIfUserUsedAppOrNot()) {
                this.copy(onboardingState = it)
            }
            Log.d("state", state.value.onboardingState.toString())
        }
        viewModelScope.launch {
            collectFlow(getUserLoginStatusUseCase()) { this.copy(isLogged = it) }
            Log.d("logged", state.value.isLogged.toString())
        }
        // tryToExecute({ manageUserUsedAppUseCase.checkIfUserUsedAppOrNot() }, ::getOnUserUsedAppForFirstTimeSuccess, ::getOnUserUsedAppForFirstTimeError)
    }

    private fun getOnUserUsedAppForFirstTimeSuccess(isFirstTime: Boolean) {
        _state.update { it.copy(onboardingState = isFirstTime) }
        Log.d("state", state.value.onboardingState.toString())
    }

    private fun getOnUserUsedAppForFirstTimeError(throwable: Throwable) {
        _state.update { it.copy(error = throwable.message) }
    }

    override fun onOrganizationNameChange(organizationName: String) {
        _state.update { it.copy(organizationName = organizationName.trim(), isLoading = false) }
    }
}