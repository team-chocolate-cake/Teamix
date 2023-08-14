package com.chocolate.viewmodel.profile

import androidx.lifecycle.ViewModel
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(

):BaseViewModel<ProfileUiState,ProfileEffect>(ProfileUiState()), ProfileInteraction {

    override fun updateLanguageDialogState(showDialog:Boolean){
        _state.update { ProfileUiState(showLanguageDialog = showDialog)}
    }
    override fun updateThemeDialogState(showDialog:Boolean){
        _state.update { ProfileUiState(showThemeDialog = showDialog)}
    }
    override fun updateLogoutDialogState(showDialog:Boolean){
        _state.update { ProfileUiState(showLogoutDialog = showDialog)}
    }

    override fun onClickOwnerPower() {
        sendUiEffect(ProfileEffect.NavigateToOwnerPower)
    }

    override fun updateClearHistoryState(showDialog:Boolean){
        _state.update { ProfileUiState(showClearHistoryDialog = showDialog)}
    }

}