package com.chocolate.viewmodel.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(

):ViewModel() {
    private val _state= MutableStateFlow(ProfileUiState())
    val state=_state.asStateFlow()

    fun updateLanguageDialogState(showDialog:Boolean){
        _state.update { ProfileUiState(showLanguageDialog = showDialog)}
    }
    fun updateThemeDialogState(showDialog:Boolean){
        _state.update { ProfileUiState(showThemeDialog = showDialog)}
    }
    fun updateLogoutDialogState(showDialog:Boolean){
        _state.update { ProfileUiState(showLogoutDialog = showDialog)}
    }
    fun updateClearHistoryState(showDialog:Boolean){
        _state.update { ProfileUiState(showClearHistoryDialog = showDialog)}
    }

}