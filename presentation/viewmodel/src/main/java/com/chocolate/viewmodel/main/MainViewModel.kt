package com.chocolate.viewmodel.main

import androidx.lifecycle.ViewModel
import com.chocolate.usecases.user.CustomizeProfileSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val customizeProfileSettingsUseCase: CustomizeProfileSettingsUseCase
): ViewModel() {
    suspend fun getLastSelectedAppLanguage() =
        customizeProfileSettingsUseCase.getLastSelectedAppLanguage()
}