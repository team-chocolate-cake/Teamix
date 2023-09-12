package com.chocolate.viewmodel.DMChat

import androidx.lifecycle.SavedStateHandle
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DMChatViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<DMChatUiState, Unit>(DMChatUiState()) {

    private val dmChatArgs = DMChatArgs(savedStateHandle)

}