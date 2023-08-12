package com.chocolate.viewmodel.home

import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.user.GetUserLoginStatusUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserLoginStatusUseCase: GetUserLoginStatusUseCase
) : BaseViewModel<HomeUiState, HomeUiEffect>(HomeUiState()) {

    init {
        getUserLoginState()
    }

     private fun getUserLoginState() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLogged = getUserLoginStatusUseCase()
                )
            }
        }
    }
}