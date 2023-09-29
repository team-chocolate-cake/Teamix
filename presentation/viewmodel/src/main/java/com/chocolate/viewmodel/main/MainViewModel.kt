package com.chocolate.viewmodel.main

import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.member.CustomizeProfileSettingsUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val customizeProfileSettings: CustomizeProfileSettingsUseCase,
) : BaseViewModel<MainUiState, Unit>(MainUiState()) {

    init {
        viewModelScope.launch {
            _state.update { it.copy(isDark = customizeProfileSettings.isDarkThemeEnabled()) }
        }
    }

    fun getLastSelectedAppLanguage() =
        customizeProfileSettings.getLatestSelectedAppLanguage()

}