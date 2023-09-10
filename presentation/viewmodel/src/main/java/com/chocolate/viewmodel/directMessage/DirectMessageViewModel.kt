package com.chocolate.viewmodel.directMessage

import com.chocolate.usecases.member.CustomizeProfileSettingsUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DirectMessageViewModel @Inject constructor(
    private val customizeProfileSettings: CustomizeProfileSettingsUseCase,
): BaseViewModel<DirectMessageUiState,Unit>(DirectMessageUiState()) , DirectMessageInteractions{
    override fun onChangeSearchQuery(search: String) {
        _state.update { it.copy(searchInput = search) }
    }

    override fun onClickDeleteQuery() {
        _state.update { it.copy(searchInput = "") }
    }


}