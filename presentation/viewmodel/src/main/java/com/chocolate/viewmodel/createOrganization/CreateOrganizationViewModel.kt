package com.chocolate.viewmodel.createOrganization

import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.chocolate.entities.exceptions.EmptyOrganizationNameException
import com.chocolate.entities.exceptions.NoConnectionException
import com.chocolate.entities.organization.Organization
import com.chocolate.usecases.organization.CreateOrganizationUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CreateOrganizationViewModel @Inject constructor(
    private val createOrganizationUseCase: CreateOrganizationUseCase,
    private val stringsResource: StringsResource,
) : BaseViewModel<CreateOrganizationUiState, CreateOrganizationUiEffect>(CreateOrganizationUiState()),
    CreateOrganizationInteraction {
    override fun onOrganizationNameChange(organizationName: String) {
        _state.update {
            it.copy(
                organizationName = organizationName.trim(),
                isLoading = false,
                error = null,
            )
        }
    }

    override fun onClickHaveOrganization() {
        sendUiEffect(CreateOrganizationUiEffect.NavigateToHaveOrganization)
    }

    override fun onClickNextButton() {
            tryToExecute(
                call = { createOrganizationUseCase.invoke(state.value.toEntity())},
                ::onCreateOrganizationSuccess,
                ::onError
            )
    }

    override fun onOrganizationImageChange(imageUri: Uri) {
        _state.update { it.copy(organizationImageUri = imageUri) }
    }


    private fun onCreateOrganizationSuccess(unit:Unit) {
        sendUiEffect(CreateOrganizationUiEffect.NavigateToCreateMemberScreen("Owner"))
    }

    private fun onError(throwable: Throwable) {
        val errorMessage = when (throwable) {
            is NoConnectionException -> stringsResource.noConnectionMessage
            is EmptyOrganizationNameException -> stringsResource.organizationNameCannotBeEmpty
            else -> stringsResource.organizationNameCannotBeEmpty
        }
        Log.e("onError: ", throwable.toString())
        _state.update { it.copy(isLoading = false, error = errorMessage) }
    }
}



