package com.chocolate.viewmodel.taskorganization

import com.chocolate.usecases.member.CustomizeProfileSettingsUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
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