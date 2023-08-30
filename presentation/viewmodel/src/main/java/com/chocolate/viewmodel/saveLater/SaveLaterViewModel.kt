package com.chocolate.viewmodel.saveLater

import androidx.lifecycle.viewModelScope
import com.chocolate.entities.messages.Message
import com.chocolate.usecases.message.ManageSaveLaterMessageUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class SaveLaterViewModel @Inject constructor(
    private val manageSaveLaterMessageUseCase: ManageSaveLaterMessageUseCase
) : BaseViewModel<SaveLaterMessageUiState, SaveLaterEffect>(SaveLaterMessageUiState()),
    SaveLaterInteraction {
    init {
        viewModelScope.launch {
            manageSaveLaterMessageUseCase.saveMassage(
                Message(
                    messageContent = "ana henaaa",
                    id =1,
                    reactions = emptyList(),
                    senderAvatarUrl ="https://media.istockphoto.com/id/1166651462/vector/cartoon-face-with-red-eyes-vector-illustration-for-anime-manga-in-japanese-style.jpg?s=612x612&w=0&k=20&c=KIyKkZte9nTt8Dv4gp_j7cnkhK3PP_UOiQm-dxmMpwA=",
                    senderEmail ="kareemesam52@yahoo.com",
                    senderId =2,
                    senderFullName ="kareem esam",
                    streamId =3,
                    timestamp = Date(12),
                    topic ="anything",
                )
            )
            manageSaveLaterMessageUseCase.saveMassage(
                Message(
                    messageContent = "ana henaaaaaaa",
                    id =2,
                    reactions = emptyList(),
                    senderAvatarUrl ="https://media.istockphoto.com/id/1166651462/vector/cartoon-face-with-red-eyes-vector-illustration-for-anime-manga-in-japanese-style.jpg?s=612x612&w=0&k=20&c=KIyKkZte9nTt8Dv4gp_j7cnkhK3PP_UOiQm-dxmMpwA=",
                    senderEmail ="kareemesam52@yahoo.com",
                    senderId =2,
                    senderFullName ="kareem esam",
                    streamId =4,
                    timestamp = Date(13),
                    topic ="anything",
                )
            )
        }

        getAllSavedMessages()
    }

    private fun getAllSavedMessages() {
        tryToExecute(
            call = { manageSaveLaterMessageUseCase.getSavedMessages() },
            ::onGetDataSuccess,
            ::onGetMessagesError
        )
    }

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
                deleteStateMessage = "message Deleted successfully",
                error = null,
                isLoading = false
            )
        }

    }

    private fun onDeleteMessageFail(e: Throwable) {
        _state.update { it.copy(error = e.message, isLoading = false) }
    }
}