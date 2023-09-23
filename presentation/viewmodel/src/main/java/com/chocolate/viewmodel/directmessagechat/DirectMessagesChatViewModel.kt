package com.chocolate.viewmodel.directmessagechat

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.chocolate.entities.messages.Message
import com.chocolate.entities.uills.Empty
import com.chocolate.usecases.directmessage.GetAllMessagesInChatUseCase
import com.chocolate.usecases.directmessage.SendMessageUseCase
import com.chocolate.usecases.member.GetCurrentMemberUseCase
import com.chocolate.usecases.message.ManageSaveLaterMessageUseCase
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
    private val sendMessageUseCase: SendMessageUseCase,
    private val getAllMessagesInChatUseCase: GetAllMessagesInChatUseCase,
    private val manageSaveLaterMessageUseCase: ManageSaveLaterMessageUseCase
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
                getAllMessagesInChatUseCase(
                    groupId = dmChatArgs.groupId,
                )
            ) { messages ->
                Log.i("teeestere", messages.map { it.toUiState(false) }.reversed().toString())
                _state.value.copy(messages = messages.map { it.toUiState(false) })
            }
        }
    }

    override fun onClickBackButton() {
        TODO("Not yet implemented")
    }


    override fun onMessageInputChanged(text: String) {
        _state.update { it.copy(messageInput = text) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onSendMessage(text: String) {
        tryToExecute(call = {
            sendMessageUseCase.invoke(
                message = getMessageDetails(text),
                currentChatId = dmChatArgs.groupId
            )
        }, onSuccess = {
            onSendMessageSuccess(it)
        }, onError = { onError(it) })


        _state.update { it.copy(messageInput = String.Empty) }
    }


    override fun onSaveMessage(message: MessageUiState) {
        viewModelScope.launch {
            manageSaveLaterMessageUseCase.addSavedLaterMessage(message.toEntity())
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

    private fun onSendMessageSuccess(unit: Unit) {
        _state.update { it.copy(error = null) }
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(error = throwable.message) }
    }

}
