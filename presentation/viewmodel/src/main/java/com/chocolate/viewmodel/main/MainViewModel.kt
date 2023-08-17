package com.chocolate.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.user.CustomizeProfileSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val customizeProfileSettingsUseCase: CustomizeProfileSettingsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(false)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch { isDarkThem() }
    }

    suspend fun getLastSelectedAppLanguage() =
        customizeProfileSettingsUseCase.getLastSelectedAppLanguage()

    suspend fun isDarkThem() {
        _state.update { customizeProfileSettingsUseCase.isDarkThem() }
    }

    suspend fun updateDarkTheme(darkTheme: Boolean) {
        _state.update { !darkTheme }
        customizeProfileSettingsUseCase.updateDarkTheme(!darkTheme)
    }
}