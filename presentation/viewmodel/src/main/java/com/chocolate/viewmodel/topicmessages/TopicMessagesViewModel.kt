package com.chocolate.viewmodel.topicmessages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import com.chocolate.entities.entity.Message
import com.chocolate.entities.util.NoConnectionException
import com.chocolate.usecases.usecase.member.GetCurrentMemberUseCase
import com.chocolate.usecases.usecase.message.ManageTopicMessagesUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.base.StringsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TopicMessagesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCurrentMemberUseCase: GetCurrentMemberUseCase,
    private val manageSaveLaterMessageUseCase: ManageTopicMessagesUseCase,
    private val stringsResource: StringsResource
) : BaseViewModel<TopicUiState, TopicMessagesEffect>(TopicUiState()), TopicMessagesInteraction {

    private val topicArgs = TopicMessagesArgs(savedStateHandle)

    init {
        _state.update { it.copy(topicName = topicArgs.topicName) }
        getAllMessages()
    }

    override fun onMessageInputChanged(text: String) {
        _state.update { it.copy(messageInput = text) }
    }

    override fun onSnackBarDismiss() {
        _state.update { it.copy(savedMessageState = null) }
    }

    override fun onSaveMessage(message: MessageUiState) {
        tryToExecute(call = { manageSaveLaterMessageUseCase.addSavedLaterMessage(message.toEntity()) },
            onSuccess = { onSavedMessageSuccess() },
            onError = { onError(it) }
        )
    }

    private fun onSavedMessageSuccess() {
        _state.update { it.copy(error = null, savedMessageState = stringsResource.savedForLater) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onSendMessage(text: String) {
        tryToExecute(
            call = {
                manageSaveLaterMessageUseCase.sendMessage(
                    message = getMessageDetails(text),
                    channelId = topicArgs.channelId.toString(),
                    topicId = topicArgs.topicId
                )
            },
            onSuccess = { _state.update { it.copy(error = null) } },
            onError = { onError(it) }
        )
        _state.update { it.copy(messageInput = "") }
    }


    private fun getAllMessages() {
        tryToExecuteFlow(
            flowBlock = {
                manageSaveLaterMessageUseCase.getMessages(
                    topicArgs.topicId,
                    topicArgs.channelId.toString()
                )
            },
            onSuccess = ::onSuccessGetMessage,
            onError = ::onError
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun getMessageDetails(text: String): Message {
        val user = getCurrentMemberUseCase.invoke()
        return Message(
            id = "",
            senderId = user.id,
            senderFullName = user.name,
            senderAvatarUrl = user.imageUrl,
            messageContent = text,
            timestamp = Date.from(
                LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()
            )
        )
    }

    private fun onSuccessGetMessage(flow: Flow<List<Message>>) {
        collectFlow(flow) { messages ->
            this.copy(messages = messages.reversed().toUiState())
        }
    }

    private fun onError(throwable: Throwable) {
        val error = when (throwable) {
            is NoConnectionException -> stringsResource.noConnectionMessage
            else -> stringsResource.globalMessageError
        }
        _state.update { it.copy(error = error) }
    }
}