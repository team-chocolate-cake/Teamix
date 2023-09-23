package com.chocolate.viewmodel.topicmessages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.chocolate.entities.messages.Message
import com.chocolate.usecases.member.GetCurrentMemberUseCase
import com.chocolate.usecases.message.ManageSaveLaterMessageUseCase
import com.chocolate.usecases.message.ManageTopicMessagesUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TopicMessagesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val manageTopicMessagesUseCase: ManageTopicMessagesUseCase,
    private val getCurrentMemberUseCase: GetCurrentMemberUseCase,
    private val manageSaveLaterMessageUseCase: ManageSaveLaterMessageUseCase,
) : BaseViewModel<TopicUiState, TopicMessagesEffect>(TopicUiState()), TopicMessagesInteraction {

    private val topicArgs = TopicMessagesArgs(savedStateHandle)

    init {
        _state.update { it.copy(topicName = topicArgs.topicName) }
        getAllMessages()
    }

    override fun onClickBackButton() {
        sendUiEffect(TopicMessagesEffect.NavigationBack)
    }


    override fun onMessageInputChanged(text: String) {
        _state.update { it.copy(messageInput = text) }
    }

    override fun onSaveMessage(message: MessageUiState) {
        viewModelScope.launch {
            manageSaveLaterMessageUseCase.addSavedLaterMessage(message.toEntity())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onSendMessage(text: String) {
        tryToExecute(
            call = {
                manageTopicMessagesUseCase.sendMessage(
                    message = getMessageDetails(text),
                    channelId = topicArgs.channelId.toString(),
                    topicId = topicArgs.topicId
                )
            },
            onSuccess = ::onSendMessageSuccess,
            onError = ::onError
        )
        _state.update { it.copy(messageInput = "") }
    }


    private fun onSendMessageSuccess(unit: Unit) {
        _state.update { it.copy(error = null) }
    }

    private fun getAllMessages() {
        viewModelScope.launch {
            tryToExecuteFlow(
                flowBlock = {
                    manageTopicMessagesUseCase.getMessages(
                        topicArgs.topicId,
                        topicArgs.channelId.toString()
                    )
                },
                onSuccess = ::onSuccessGetMessage,
                onError = ::onError
            )
        }
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

    private suspend fun onSuccessGetMessage(flow: Flow<List<Message>>) {
        flow.collectLatest { messages ->
            messages.map {
                _state.update {
                    it.copy(
                        messages = messages.toUiState(false)
                    )
                }
            }
        }
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(error = throwable.message) }
    }
}