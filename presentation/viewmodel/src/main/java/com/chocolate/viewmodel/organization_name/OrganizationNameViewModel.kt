package com.chocolate.viewmodel.organization_name

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OrganizationNameViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(OrganizationNameUiState())
    val state = _state.asStateFlow()

    fun updateOrganizationName(name:String){
        _state.update {
            it.copy(
                nameOrganization = name,
            )
        }
    }
}