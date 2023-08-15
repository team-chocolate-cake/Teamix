package com.chocolate.viewmodel.home

import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.user.UserInformationUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userInformationUseCase: UserInformationUseCase
) : BaseViewModel<HomeUiState, HomeUiEffect>(HomeUiState()) {

    init {
        viewModelScope.launch(Dispatchers.Main) {
            getUserLoginState()
        }
    }

    private suspend fun getUserLoginState() {
        collectFlow(userInformationUseCase.getUserLoginStatus()) {
            this.copy(
                isLogged = it,
            )
        }
    }
}