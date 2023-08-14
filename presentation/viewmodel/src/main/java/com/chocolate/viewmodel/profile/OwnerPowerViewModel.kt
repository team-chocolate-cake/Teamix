package com.chocolate.viewmodel.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OwnerPowerViewModel @Inject constructor(

):ViewModel(),OwnerPowerInteraction {

    private val _state= MutableStateFlow(OwnerPowerUiState())
    val state=_state.asStateFlow()

    override fun updateOrganizationNameSheetState(showSheet:Boolean){
        _state.update { OwnerPowerUiState(showOrganizationNameSheet = showSheet) }
    }
    override fun updateOrganizationImageState(showSheet:Boolean){
        _state.update { OwnerPowerUiState(showOrganizationImageSheet = showSheet)}
    }
    override fun updateChangeMemberRoleDialogState(showDialog:Boolean){
        _state.update { OwnerPowerUiState(showChangeMemberRoleDialog = showDialog)}
    }
    override fun updateCreateChannelSheetState(showSheet:Boolean){
        _state.update { OwnerPowerUiState(showCreateChannelSheet = showSheet)}
    }

}