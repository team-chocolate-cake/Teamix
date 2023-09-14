package com.chocolate.viewmodel.DMChat

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.chocolate.entities.directMessage.MessageEntity
import com.chocolate.entities.uills.Empty
import com.chocolate.usecases.direct_message.GetAllMessagesInChatUseCase
import com.chocolate.usecases.direct_message.SendMessageUseCase
import com.chocolate.usecases.member.GetCurrentMemberUseCase
import com.chocolate.usecases.organization.ManageOrganizationDetailsUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.topic.ReactionUiState
import com.chocolate.viewmodel.topic.TopicInteraction
import com.chocolate.viewmodel.topic.TopicUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class DMChatViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCurrentMemberUseCase: GetCurrentMemberUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val manageOrganizationDetailsUseCase: ManageOrganizationDetailsUseCase,
    private val getAllMessagesInChatUseCase: GetAllMessagesInChatUseCase
) : BaseViewModel<TopicUiState, Unit>(TopicUiState()), TopicInteraction {

    private val dmChatArgs = DMChatArgs(savedStateHandle)

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

    override fun openEmojisTile() {
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
                message = MessageEntity(
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

    override fun onStartVoiceRecording() {
        TODO("Not yet implemented")
    }

    override fun onClickCamera() {
        TODO("Not yet implemented")
    }

    override fun onClickPhotoOrVideo(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onAddReactionToMessage(messageId: Int) {
        TODO("Not yet implemented")
    }

    override fun onSaveMessage() {
        TODO("Not yet implemented")
    }

    override fun onGetNotification() {
        TODO("Not yet implemented")
    }

    override fun onPinMessage() {
        TODO("Not yet implemented")
    }

    override fun onOpenReactTile() {
        TODO("Not yet implemented")
    }

    override fun onClickReact(positive: Boolean, reactionUiState: ReactionUiState) {
        TODO("Not yet implemented")
    }

}
