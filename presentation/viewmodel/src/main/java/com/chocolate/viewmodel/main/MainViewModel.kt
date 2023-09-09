package com.chocolate.viewmodel.main

import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.member.CustomizeProfileSettingsUseCase
import com.chocolate.usecases.member.IsMemberLoggedInUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val customizeProfileSettings: CustomizeProfileSettingsUseCase,
    private val getUserLoginStatus: IsMemberLoggedInUseCase,
) : BaseViewModel<MainUiState, Unit>(MainUiState()) {
    init {
        _state.update { it.copy(isLoggedIn = getUserLoginStatus())
        }
    }

    suspend fun getLastSelectedAppLanguage() =
        customizeProfileSettings.getLatestSelectedAppLanguage()


    private suspend fun isDarkThem() {
        _state.update {
            it.copy(
                isDark = flowOf(true)
            )
        }
    }
}