package com.chocolate.viewmodel.organization_name

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.chocolate.entities.exceptions.EmptyOrganizationNameException
import com.chocolate.entities.exceptions.NoConnectionException
import com.chocolate.entities.exceptions.OrganizationNotFoundException
import com.chocolate.usecases.onboarding.ManageUserUsedAppUseCase
import com.chocolate.usecases.organization.ManageOrganizationDetailsUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrganizationNameViewModel @Inject constructor(
    private val manageOrganizationDetails: ManageOrganizationDetailsUseCase,
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
            { manageOrganizationDetails.organizationSignIn(organizationName) },
            ::onOrganizationSignInSuccess,
            ::onError
        )
    }

    private fun onOrganizationSignInSuccess(organizationName: String) {
        tryToExecute(
            { manageOrganizationDetails.saveOrganizationName(organizationName) },
            ::onSavingOrganizationNameSuccess,
            ::onError
        )
    }

    override fun onOrganizationNameChange(organizationName: String) {
        _state.update {
            it.copy(
                organizationName = organizationName.trim(),
                isLoading = false,
                error = null,
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
        viewModelScope.launch {
            collectFlow(manageUserUsedApp.checkIfUserUsedAppOrNot()) {
                this.copy(onboardingState = it)
            }
        }
    }

    private fun onError(throwable: Throwable) {
        val errorMessage = when (throwable) {
            is NoConnectionException -> stringsResource.noConnectionMessage
            is OrganizationNotFoundException -> stringsResource.organizationNameNotFound
            is EmptyOrganizationNameException -> stringsResource.organizationNameCannotBeEmpty
            else -> stringsResource.organizationNameCannotBeEmpty
        }
        Log.e("onError: ", throwable.toString())
        _state.update { it.copy(isLoading = false, error = errorMessage) }
        sendUiEffect(OrganizationNameUiEffect.ShowSnackBar)
    }
}