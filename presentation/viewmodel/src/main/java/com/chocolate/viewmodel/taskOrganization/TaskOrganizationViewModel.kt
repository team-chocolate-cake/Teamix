package com.chocolate.viewmodel.taskOrganization

import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.member.CustomizeProfileSettingsUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskOrganizationViewModel @Inject constructor(
    private val customizeProfileSettings: CustomizeProfileSettingsUseCase,
): BaseViewModel<TaskOrganizationUiState,Unit>(TaskOrganizationUiState()){

    init {
        getDarkModeState()
    }

    private fun getDarkModeState() {
        /*viewModelScope.launch {
           customizeProfileSettings.isDarkThemeEnabled().collectLatest { isDark->
                _state.update { it.copy(isDarkMode = isDark) }
            }
        }*/
    }

}