package com.chocolate.viewmodel.main

import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.user.CustomizeProfileSettingsUseCase
import com.chocolate.usecases.user.GetUserLoginStatusUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val customizeProfileSettings: CustomizeProfileSettingsUseCase,
    private val getUserLoginStatus: GetUserLoginStatusUseCase,
) : BaseViewModel<MainUiState, Unit>(MainUiState()) {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            isDarkThem()
            launch(Dispatchers.Default) {
                delay(500)
                _state.update {
                    it.copy(
                        isLoggedIn = getUserLoginStatus()
                    )
                }
            }
        }
    }

    suspend fun getLastSelectedAppLanguage() =
        customizeProfileSettings.getLastSelectedAppLanguage()

    private suspend fun isDarkThem() {
        _state.update {
            it.copy(
                isDark = customizeProfileSettings.isDarkThem()
            )
        }
    }
}