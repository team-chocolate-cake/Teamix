package com.chocolate.viewmodel.forgetPassowrd

import com.chocolate.usecases.organization.GetNameOrganizationsUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(
    private val getNameOrganizationsUseCase: GetNameOrganizationsUseCase
) : BaseViewModel<ForgetPasswordUiState, ForgetPasswordEffect>(ForgetPasswordUiState()) {

    init {
        getOrganizationName()
    }

    private fun getOrganizationName() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            { getNameOrganizationsUseCase() },
            ::onGetOrganizationNameSuccess,
            ::onGetOrganizationNameError
        )
    }

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