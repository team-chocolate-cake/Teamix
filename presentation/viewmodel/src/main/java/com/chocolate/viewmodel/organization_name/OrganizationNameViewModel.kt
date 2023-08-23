package com.chocolate.viewmodel.organization_name

import com.chocolate.entities.exceptions.NoConnectionException
import com.chocolate.usecases.onboarding.ManageUserUsedAppUseCase
import com.chocolate.usecases.organization.SaveNameOrganizationUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OrganizationNameViewModel @Inject constructor(
    private val saveNameOrganizationsUseCase: SaveNameOrganizationUseCase,
    private val manageUserUsedAppUseCase: ManageUserUsedAppUseCase,
    private val stringsRes: StringsRes
) : BaseViewModel<OrganizationNameUiState, OrganizationNameUiEffect>(OrganizationNameUiState()),
    OrganizationNameInteraction {

    init {
        getOnboardingState()
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
            is NoConnectionException -> stringsRes.noConnectionMessage
            else -> stringsRes.organizationNameCannotBeEmpty
        }
        _state.update { it.copy(isLoading = false, error = errorMessage) }
    }

    private fun getOnboardingState() {
        tryToExecute({ manageUserUsedAppUseCase.checkIfUserUsedAppOrNot() }, ::getOnboardingSuccess, ::getOnboardingError)
    }

    private fun getOnboardingSuccess(isFirstTime: Boolean) {
        _state.update { it.copy(onboardingState = isFirstTime) }
    }

    private fun getOnboardingError(throwable: Throwable) {
        _state.update { it.copy(error = throwable.message) }
    }

    override fun onOrganizationNameChange(organizationName: String) {
        _state.update { it.copy(organizationName = organizationName.trim(), isLoading = false) }
    }
}