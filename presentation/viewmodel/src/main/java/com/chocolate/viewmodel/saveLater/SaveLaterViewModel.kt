package com.chocolate.viewmodel.saveLater

import com.chocolate.entities.messages.Message
import com.chocolate.usecases.message.ManageSaveLaterMessageUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SaveLaterViewModel @Inject constructor(
    private val manageSaveLaterMessageUseCase: ManageSaveLaterMessageUseCase,
    private val stringsResource: StringsResource
) : BaseViewModel<SaveLaterMessageUiState, SaveLaterEffect>(SaveLaterMessageUiState()),
    SaveLaterInteraction {
    init {
      //  getAllSavedMessages()
    }

//    private fun getAllSavedMessages() {
//        tryToExecute(
//            call = { manageSaveLaterMessageUseCase.getSavedMessages() },
//            ::onGetDataSuccess,
//            ::onGetMessagesError
//        )
//    }

    private fun onGetDataSuccess(data: List<Message>) {
        _state.update { state ->
            state.copy(
                messages = data.map { it.toMessageUiState() },
                error = null,
                isLoading = false,
            )
        }
    }

    private fun onGetMessagesError(e: Throwable) {
        _state.update { it.copy(error = e.message, isLoading = false) }
    }

    override fun onDismissMessage(messageId: Int) {
        tryToExecute(
            call = { manageSaveLaterMessageUseCase.deleteSavedMessageById(messageId) },
            ::onDeleteMessageSuccess,
            ::onDeleteMessageFail
        )
        _state.update {state->
            val newMessages = state.messages.filter { it.id != messageId }
            state.copy(messages = newMessages)
        }
    }

    override fun onDeleteStateDismiss() =
        _state.update { it.copy(deleteStateMessage = null, isLoading = false) }


    override fun onErrorDismiss() = _state.update { it.copy(error = null, isLoading = false) }

    private fun onDeleteMessageSuccess(unit: Unit) {
        _state.update {
            it.copy(
                deleteStateMessage = stringsResource.messageDeletedSuccessfully,
                error = null,
                isLoading = false
            )
        }

    }

    private fun onDeleteMessageFail(e: Throwable) {
        _state.update { it.copy(error = e.message, isLoading = false) }
    }
}