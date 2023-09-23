package com.chocolate.viewmodel.savedlater

import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.message.ManageSaveLaterMessageUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveLaterViewModel @Inject constructor(
    private val manageSaveLaterMessageUseCase: ManageSaveLaterMessageUseCase,
    private val stringsResource: StringsResource
) : BaseViewModel<SaveLaterMessageUiState, SaveLaterEffect>(SaveLaterMessageUiState()),
    SaveLaterInteraction {

    init {
        getSavedLaterMessages()
    }

    private fun getSavedLaterMessages() {
        viewModelScope.launch {
            collectFlow(manageSaveLaterMessageUseCase.getSavedMessages()) {
                this.copy(messages = it.toMessagesUiState(), error = null, isLoading = false,)
            }
        }
    }

    override fun onDismissMessage(savedLaterMessageId: String) {
        tryToExecute(
            { manageSaveLaterMessageUseCase.deleteSavedMessageById(savedLaterMessageId) },
            ::onDeleteMessageSuccess,
            ::onError
        )
    }

    private fun onDeleteMessageSuccess(unit: Unit) {
        _state.update {
            it.copy(
                deleteStateMessage = stringsResource.messageDeletedSuccessfully,
                error = null,
                isLoading = false
            )
        }
    }

    private fun onError(e: Throwable) {
        _state.update { it.copy(error = e.message, isLoading = false) }
    }
}