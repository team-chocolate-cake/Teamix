package com.chocolate.viewmodel.direct_messages.specific_dm

import androidx.lifecycle.viewModelScope
import com.chocolate.usecases.direct_messages.ManageMessagesUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class DirectMessageViewModel @Inject constructor(
    private val manageMessage: ManageMessagesUseCase
) : BaseViewModel<DirectMessageUiState, DirectMessageUiEffect>(
    DirectMessageUiState()
), DirectMessageInteraction {

    init {
        getData()
    }

    private fun getData() {
        //uploadFile()
       // deleteMessage()
       // editMessage()
       // sendDirectMessage()
    }

    private fun sendDirectMessage(type: String, recipients: String, content: String) {
        tryToExecute(
            { manageMessage.sendDirectMessage(type, recipients, content) },
            ::onSendMessageSuccess, ::onError
        )
    }

    private fun onSendMessageSuccess(isSent: Boolean) {
        _state.update { it.copy(isSent = isSent) }
    }

    private fun editMessage(messageId: Int, content: String, topic: String = "") {
        viewModelScope.launch { manageMessage.editMessage(messageId, content, topic) }
    }

    private fun deleteMessage(messageId: Int) {
        viewModelScope.launch { manageMessage.deleteMessage(messageId) }
    }

    private fun uploadFile(file: File) {
        viewModelScope.launch { manageMessage.uploadFile(file) }
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(error = throwable.message) }

    }
}