package com.chocolate.viewmodel.organizationname

import com.chocolate.entities.util.EmptyOrganizationNameException
import com.chocolate.entities.util.NoConnectionException
import com.chocolate.entities.util.OrganizationNotFoundException
import com.chocolate.usecases.onboarding.ManageUserUsedAppUseCase
import com.chocolate.usecases.organization.ManageOrganizationUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OrganizationNameViewModel @Inject constructor(
    private val manageOrganizationUseCase: ManageOrganizationUseCase,
    private val stringsResource: StringsResource,
    private val manageUserUsedApp: ManageUserUsedAppUseCase,
) : BaseViewModel<OrganizationNameUiState, OrganizationNameUiEffect>(OrganizationNameUiState()),
    OrganizationNameInteraction {
    init {
        getOnboardingStatus()
    }

    override fun onClickCreateOrganization() {
        sendUiEffect(OrganizationNameUiEffect.NavigateToCreateOrganization)
    }

    override fun onEnterButtonClick(organizationName: String) {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            { manageOrganizationUseCase.organizationSignIn(organizationName) },
            ::onOrganizationSignInSuccess,
            ::onError
        )
    }

    override fun clearError() {
        _state.update { it.copy(error = null) }
    }

    private fun onOrganizationSignInSuccess(organizationName: String) {
        tryToExecute(
            { manageOrganizationUseCase.saveOrganizationName(organizationName) },
            ::onSavingOrganizationNameSuccess,
            ::onError
        )
    }

    override fun onOrganizationNameChange(organizationName: String) {
        _state.update {
            it.copy(
                organizationName = organizationName,
                isLoading = false,
                error = null
            )
        }
    }

    private fun onSavingOrganizationNameSuccess(isSaved: Boolean) {
        if (isSaved) {
            _state.update { it.copy(isLoading = false, error = null) }
            sendUiEffect(OrganizationNameUiEffect.NavigateToLoginScreen)
        }
    }

    private fun getOnboardingStatus() {
        collectFlow(manageUserUsedApp.checkIfUserUsedAppOrNot()) {
            this.copy(onboardingState = it)
        }
    }

    private fun onError(throwable: Throwable) {
        val errorMessage = when (throwable) {
            is NoConnectionException -> stringsResource.noConnectionMessage
            is OrganizationNotFoundException -> stringsResource.organizationNameNotFound
            is EmptyOrganizationNameException -> stringsResource.organizationNameCannotBeEmpty
            else -> stringsResource.organizationNameCannotBeEmpty
        }
        _state.update { it.copy(isLoading = false, error = errorMessage) }
    }
}