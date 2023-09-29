package com.chocolate.viewmodel.directmessagechat

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.chocolate.entities.entity.Message
import com.chocolate.entities.util.Empty
import com.chocolate.usecases.directmessage.ManageDirectMessageUseCase
import com.chocolate.usecases.member.GetCurrentMemberUseCase
import com.chocolate.usecases.message.ManageTopicMessagesUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.topicmessages.MessageUiState
import com.chocolate.viewmodel.topicmessages.TopicMessagesInteraction
import com.chocolate.viewmodel.topicmessages.TopicUiState
import com.chocolate.viewmodel.topicmessages.toEntity
import com.chocolate.viewmodel.topicmessages.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class DirectMessagesChatViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCurrentMemberUseCase: GetCurrentMemberUseCase,
    private val manageDirectMessageUseCase: ManageDirectMessageUseCase,
) : BaseViewModel<TopicUiState, Unit>(TopicUiState()), TopicMessagesInteraction {

    private val dmChatArgs = DirectMessageChatArgs(savedStateHandle)

    init {
        _state.update {
            it.copy(
                topicName = dmChatArgs.memberName
            )
        }
        getAllMessages()
    }

    private fun getAllMessages() {
        viewModelScope.launch {
            collectFlow(
                manageDirectMessageUseCase.getAllMessagesInChat(
                    groupId = dmChatArgs.groupId,
                )
            ) { messages ->
                _state.value.copy(messages = messages.map { it.toUiState() })
            }
        }
    }

    override fun onMessageInputChanged(text: String) {
        _state.update { it.copy(messageInput = text) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onSendMessage(text: String) {
        tryToExecute(call = {
            manageDirectMessageUseCase.sendMessage(
                message = getMessageDetails(text),
                currentChatId = dmChatArgs.groupId
            )
        }, onSuccess = {
            onSendMessageSuccess(it)
        }, onError = { onError(it) })
        _state.update { it.copy(messageInput = String.Empty) }
    }


    override fun onSaveMessage(message: MessageUiState) {
        tryToExecute(
            call = { manageDirectMessageUseCase.addSavedLaterMessage(message.toEntity()) },
            onSuccess = { _state.update { it.copy(error = null) } },
            onError = { onError(it) }
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

    private fun onSendMessageSuccess(unit: Unit) {
        _state.update { it.copy(error = null) }
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(error = throwable.message) }
    }

}
