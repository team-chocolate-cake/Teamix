package com.chocolate.viewmodel.directMessage

import com.chocolate.entities.user.User
import com.chocolate.usecases.user.GetAllUsersUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DirectMessageViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase
): BaseViewModel<DirectMessageUiState,Unit>(DirectMessageUiState()) {

    init {
        getData()
    }

    private fun getData() {
        tryToExecute({getAllUsersUseCase()},::onGetAllUserSuccess,::onError)
    }

    private fun onGetAllUserSuccess(users: List<User>) {
        _state.update { it.copy(membersUiState = users.toUiState()) }
    }

    private fun onError(throwable: Throwable) {

    }
}