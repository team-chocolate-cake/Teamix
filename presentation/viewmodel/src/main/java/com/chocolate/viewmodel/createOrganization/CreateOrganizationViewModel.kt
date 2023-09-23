package com.chocolate.viewmodel.createOrganization

import android.net.Uri
import android.util.Log
import com.chocolate.entities.exceptions.EmptyOrganizationNameException
import com.chocolate.entities.exceptions.NoConnectionException
import com.chocolate.usecases.organization.CreateOrganizationUseCase
import com.chocolate.usecases.organization.ManageOrganizationDetailsUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class CreateOrganizationViewModel @Inject constructor(
    private val createOrganizationUseCase: CreateOrganizationUseCase,
    private val stringsResource: StringsResource,
    private val manageOrganizationDetailsUseCase: ManageOrganizationDetailsUseCase
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

    override fun onClickNextButton(snakeBar:Boolean) {
        _state.update { it.copy(isLoading = true, showSnakeBar = !snakeBar) }
        tryToExecute(
            { createOrganizationUseCase.invoke(state.value.toEntity()) },
            ::onCreateOrganizationSignInSuccess,
            ::onError
        )
    }

    override fun onOrganizationImageChange(imageUri: Uri) {
        _state.update { it.copy(organizationImageUri = imageUri) }
    }


    private fun onCreateOrganizationSignInSuccess(unit: Unit) {
        tryToExecute(
            { manageOrganizationDetailsUseCase.saveOrganizationName(state.value.organizationName) },
            ::onSavingOrganizationNameSuccess,
            ::onError
        )
    }

    private fun onSavingOrganizationNameSuccess(isSaved: Boolean) {
        if (isSaved) {
            _state.update { it.copy(isLoading = false, error = null) }
            sendUiEffect(CreateOrganizationUiEffect.NavigateToCreateMemberScreen("Owner"))
        }
    }

    private fun onError(throwable: Throwable) {
        val errorMessage = when (throwable) {
            is NoConnectionException -> stringsResource.noConnectionMessage
            is EmptyOrganizationNameException -> stringsResource.organizationNameOrImageCannotBeEmpty
            else -> stringsResource.organizationNameOrImageCannotBeEmpty
        }
        _state.update { it.copy(isLoading = false, error = errorMessage) }
    }
}



