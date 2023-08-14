package com.chocolate.viewmodel.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OwnerPowerViewModel @Inject constructor(

):ViewModel() {

    private val _state= MutableStateFlow(OwnerPowerUiState())
    val state=_state.asStateFlow()

    fun updateOrganizationNameSheetState(showSheet:Boolean){
        _state.update { OwnerPowerUiState(showOrganizationNameSheet = showSheet) }
    }
    fun updateOrganizationImageState(showSheet:Boolean){
        _state.update { OwnerPowerUiState(showOrganizationImageSheet = showSheet)}
    }
    fun updateChangeMemberRoleDialogState(showDialog:Boolean){
        _state.update { OwnerPowerUiState(showChangeMemberRoleDialog = showDialog)}
    }
    fun updateCreateChannelSheetState(showSheet:Boolean){
        _state.update { OwnerPowerUiState(showCreateChannelSheet = showSheet)}
    }

}