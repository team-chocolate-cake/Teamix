package com.chocolate.viewmodel.directMessageChat

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.chocolate.entities.directMessage.DirectMessage
import com.chocolate.entities.uills.Empty
import com.chocolate.usecases.directmessage.GetAllMessagesInChatUseCase
import com.chocolate.usecases.directmessage.SendMessageUseCase
import com.chocolate.usecases.member.GetCurrentMemberUseCase
import com.chocolate.usecases.organization.ManageOrganizationDetailsUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.topicMessages.MessageUiState
import com.chocolate.viewmodel.topicMessages.TopicMessagesInteraction
import com.chocolate.viewmodel.topicMessages.TopicUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class DirectMessagesChatViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCurrentMemberUseCase: GetCurrentMemberUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val manageOrganizationDetailsUseCase: ManageOrganizationDetailsUseCase,
    private val getAllMessagesInChatUseCase: GetAllMessagesInChatUseCase
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
                    currentOrgName = manageOrganizationDetailsUseCase.getOrganizationName()
                )
            ) { messages ->
                _state.value.copy(messages = messages.map { it.toUiState() }.reversed())
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
        viewModelScope.launch {
            val user = getCurrentMemberUseCase.invoke()
            val orgName = manageOrganizationDetailsUseCase.getOrganizationName()
            sendMessageUseCase.invoke(
                directMessage = DirectMessage(
                    sentBy = user.id,
                    messageContent = text,
                    sentAt = Calendar.getInstance().getTime(),
                    senderFullName = user.name,
                    senderImageUrl = user.imageUrl,
                ),
                currentChatId = dmChatArgs.groupId,
                currentOrgName = orgName
            )
        }
        _state.update { it.copy(messageInput = String.Empty) }
    }




    override fun onSaveMessage(message: MessageUiState) {
        TODO("Not yet implemented")
    }

}
