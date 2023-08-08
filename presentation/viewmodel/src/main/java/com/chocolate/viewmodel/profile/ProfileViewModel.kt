package com.chocolate.viewmodel.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ProfileViewModel @Inject constructor(

):ViewModel() {
    private val _state= MutableStateFlow(ProfileUiState())
    val state=_state.asStateFlow()
}