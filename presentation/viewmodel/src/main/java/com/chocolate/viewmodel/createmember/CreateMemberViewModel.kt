package com.chocolate.viewmodel.createmember

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import com.chocolate.entities.util.InvalidEmailException
import com.chocolate.entities.util.InvalidUsernameException
import com.chocolate.entities.util.MemberAlreadyExistException
import com.chocolate.entities.util.PasswordMismatchException
import com.chocolate.usecases.member.AttemptMemberLoginUseCase
import com.chocolate.usecases.member.CreateMemberUseCase
import com.chocolate.usecases.organization.CreateOrganizationUseCase
import com.chocolate.usecases.organization.ManageOrganizationDetailsUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import com.chocolate.viewmodel.choosemember.CreateMemberArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import android.util.Base64
import com.chocolate.entities.entity.Organization
import com.chocolate.entities.util.EmptyEmailException
import com.chocolate.entities.util.EmptyFullNameException
import com.chocolate.entities.util.EmptyImageUriException
import com.chocolate.entities.util.EmptyPasswordException
import javax.inject.Inject

@HiltViewModel
class CreateMemberViewModel @Inject constructor(
    private val createMember: CreateMemberUseCase,
    private val stringsResource: StringsResource,
    private val attemptMemberLoginUseCase: AttemptMemberLoginUseCase,
    private val createOrganizationUseCase: CreateOrganizationUseCase,
    private val manageOrganizationDetailsUseCase: ManageOrganizationDetailsUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<CreateMemberUiState, CreateMemberUiEffect>(CreateMemberUiState()),
    CreateMemberInteraction {
    private val createMemberArgs = CreateMemberArgs(savedStateHandle)

    init {
        _state.update { it.copy(isCreatingMember = createMemberArgs.role == "Member") }
    }

    override fun onFullNameChange(name: String) {
        _state.update { it.copy(fullName = name) }
    }

    override fun onEmailChange(email: String) {
        _state.update { it.copy(email = email) }
    }

    override fun onPasswordChange(password: String) {
        _state.update { it.copy(password = password) }
    }

    override fun onErrorDismiss() {
        _state.update { it.copy(error = null) }
    }

    override fun onConfirmPasswordChange(password: String) {
        _state.update { it.copy(confirmPassword = password) }
    }

    override fun onPasswordVisibilityChange(newPasswordVisibility: Boolean) {
        _state.update { it.copy(passwordVisibility = newPasswordVisibility) }
    }

    override fun onConfirmPasswordVisibilityChange(newPasswordVisibility: Boolean) {
        _state.update { it.copy(confirmPasswordVisibility = newPasswordVisibility) }
    }

    override fun onCreateButtonClick() {
        _state.update { it.copy(isLoading = true, error = null) }
        tryToExecute(
            call = {
                if (createMemberArgs.role == "Member")
                    createMember(
                        state.value.toEntity(createMemberArgs.role),
                        state.value.confirmPassword
                    )
                else
                    createOrganizationUseCase.createOrganizationAndOwner(
                        Organization(
                            manageOrganizationDetailsUseCase.getOrganizationName(),
                            String(Base64.decode(createMemberArgs.imageUri!!, Base64.DEFAULT)),
                            ""
                        ),
                        state.value.toEntity(createMemberArgs.role),
                        state.value.confirmPassword
                    )
            },
            onSuccess = ::onCreateSuccess,
            onError = ::onError
        )
    }

    private fun onCreateSuccess(unit: Unit) {
        _state.update { it.copy(error = null, isLoading = false) }
        tryToExecute(
            { attemptMemberLoginUseCase(state.value.email, state.value.password) },
            ::onSavingLoginStateSuccess,
            ::onError
        )
    }

    private fun onSavingLoginStateSuccess(unit: Unit) {
        sendUiEffect(CreateMemberUiEffect.NavigateToHome)
    }

    private fun onError(throwable: Throwable) {
        val errorMessage = when (throwable) {
            is EmptyPasswordException -> stringsResource.emptyPassword
            is EmptyFullNameException -> stringsResource.emptyFullNameMessage
            is EmptyEmailException -> stringsResource.emptyEmailMessage
            is EmptyImageUriException -> stringsResource.invalidImage
            is PasswordMismatchException -> stringsResource.passwordMismatch
            is MemberAlreadyExistException -> stringsResource.memberAlreadyExist
            is InvalidUsernameException -> stringsResource.invalidUsername
            is InvalidEmailException -> stringsResource.invalidEmail
            else -> stringsResource.globalMessageError
        }
        _state.update { it.copy(error = errorMessage, isLoading = false) }
    }

    override fun onSignInClick() {
        sendUiEffect(CreateMemberUiEffect.NavigateToLogin)
    }

    override fun onPersonalImageChange(newUri: Uri) {
        _state.update { it.copy(personalImageUri = newUri) }
    }
}