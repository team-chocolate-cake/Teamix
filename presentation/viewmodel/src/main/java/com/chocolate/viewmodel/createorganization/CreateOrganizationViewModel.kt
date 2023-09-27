package com.chocolate.viewmodel.createorganization

import android.net.Uri
import com.chocolate.entities.util.EmptyOrganizationNameException
import com.chocolate.entities.util.NoConnectionException
import com.chocolate.usecases.organization.CreateOrganizationUseCase
import com.chocolate.usecases.organization.ManageOrganizationDetailsUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import android.util.Base64
import com.chocolate.entities.utils.InvalidOrganizationImageUrl
import com.chocolate.entities.utils.OrganizationAlreadyExistException
import com.chocolate.entities.utils.OrganizationNameIsSoLongException
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
            it.copy(organizationName = organizationName.trim(), isLoading = false, error = null)
        }
    }

    override fun onClickHaveOrganization() {
        sendUiEffect(CreateOrganizationUiEffect.NavigateToHaveOrganization)
    }

    override fun onClickNextButton() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            {
                createOrganizationUseCase.validateOrganizationData(
                    state.value.organizationName,
                    state.value.organizationImageUri
                )
            },
            ::onOrganizationDataValidationSuccess,
            ::onError
        )
    }

    override fun onSnackBarDismissed() {
        _state.update { it.copy(error = null) }
    }

    override fun onOrganizationImageChange(imageUri: Uri) {
        _state.update { it.copy(organizationImageUri = imageUri.toString()) }
    }

    private fun onOrganizationDataValidationSuccess(unit: Unit) {
        tryToExecute(
            { manageOrganizationDetailsUseCase.saveOrganizationName(state.value.organizationName) },
            ::onSavingOrganizationNameSuccess,
            ::onError
        )
    }

    private fun onSavingOrganizationNameSuccess(isSaved: Boolean) {
        if (isSaved) {
            _state.update { it.copy(isLoading = false, error = null) }
            sendUiEffect(
                CreateOrganizationUiEffect.NavigateToCreateMemberScreen(
                    "Owner",
                    Base64.encodeToString(state.value.organizationImageUri.toByteArray(), Base64.DEFAULT)
                )
            )
        }
    }

    private fun onError(throwable: Throwable) {
        val errorMessage = when (throwable) {
            is NoConnectionException -> stringsResource.noConnectionMessage
            is EmptyOrganizationNameException -> stringsResource.organizationNameCannotBeEmpty
            is OrganizationAlreadyExistException -> stringsResource.organizationNameAlreadyExist
            is OrganizationNameIsSoLongException -> stringsResource.organizationNameIsSoLongException
            is InvalidOrganizationImageUrl -> stringsResource.invalidImage
            else -> stringsResource.organizationNameOrImageCannotBeEmpty
        }
        _state.update { it.copy(isLoading = false, error = errorMessage) }
    }
}