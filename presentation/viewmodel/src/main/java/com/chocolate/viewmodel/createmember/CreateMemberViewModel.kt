package com.chocolate.viewmodel.createmember

import android.net.Uri
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.chocolate.entities.util.InvalidEmailException
import com.chocolate.entities.util.InvalidUsernameException
import com.chocolate.entities.util.MemberAlreadyExistException
import com.chocolate.entities.util.MissingRequiredFieldsException
import com.chocolate.entities.util.PasswordMismatchException
import com.chocolate.usecases.member.AttemptMemberLoginUseCase
import com.chocolate.usecases.member.CreateMemberUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import com.chocolate.viewmodel.choosemember.CreateMemberArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CreateMemberViewModel @Inject constructor(
    private val createMember: CreateMemberUseCase,
    private val stringsResource: StringsResource,
    private val attemptMemberLoginUseCase: AttemptMemberLoginUseCase,
    savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<CreateMemberUiState, CreateMemberUiEffect>(CreateMemberUiState()),
    CreateMemberInteraction {
private val createMemberArgs=CreateMemberArgs(savedStateHandle)
    override fun onFullNameChange(name: String) {
        _state.update { it.copy(fullName = name) }
    }

    override fun onEmailChange(email: String) {
        _state.update { it.copy(email = email) }
    }

    override fun onPasswordChange(password: String) {
        _state.update { it.copy(password = password) }
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

    override fun onCreateAccountClick() {
        _state.update { it.copy(isLoading = true, error = null) }
        tryToExecute(
            call = {
                createMember(
                    state.value.toEntity(createMemberArgs.role),
                    state.value.confirmPassword
                )
            },
            onSuccess = ::onCreateAccountSuccess,
            onError = ::onError
        )
    }

    private fun onCreateAccountSuccess(unit: Unit) {
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
            is MissingRequiredFieldsException -> stringsResource.allFieldsAreRequired
            is PasswordMismatchException -> stringsResource.passwordMismatch
            is MemberAlreadyExistException -> stringsResource.memberAlreadyExist
            is InvalidUsernameException -> stringsResource.invalidUsername
            is InvalidEmailException -> stringsResource.invalidEmail
            else -> stringsResource.globalMessageError
        }

        _state.update { it.copy(error = errorMessage, isLoading = false) }
        Log.e("err", "onError: ${_state.value.error}")
    }

    override fun onSignInClick() {
        sendUiEffect(CreateMemberUiEffect.NavigateToLogin)
    }

    override fun onPersonalImageChange(newUri: Uri) {
        _state.update { it.copy(personalImageUri = newUri) }
    }

}