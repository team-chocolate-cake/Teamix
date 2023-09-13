package com.chocolate.viewmodel.createAccount

import com.chocolate.usecases.organization.ManageOrganizationDetailsUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CreateNewAccountViewModel @Inject constructor(
    private val manageOrganizationDetails: ManageOrganizationDetailsUseCase
) : BaseViewModel<CreateNewAccountUiState, Unit>(CreateNewAccountUiState()) {
    init {
        //getOrganizationName()
    }

   /* private fun getOrganizationName() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            manageOrganizationDetails::getOrganizationName,
            ::onGetOrganizationNameSuccess,
            ::onGetOrganizationNameError
        )
    }*/

    private fun onGetOrganizationNameSuccess(organizationName: String) {
        _state.update {
            it.copy(
                isLoading = false,
                error = null,
                organizationsName = organizationName
            )
        }
    }

    private fun onGetOrganizationNameError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message) }
    }
}