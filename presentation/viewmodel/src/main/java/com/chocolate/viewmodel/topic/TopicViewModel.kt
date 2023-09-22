package com.chocolate.viewmodel.topic

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
class TopicViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val manageTopicMessagesUseCase: ManageTopicMessagesUseCase,
    private val getCurrentMemberUseCase: GetCurrentMemberUseCase,
    private val manageSaveLaterMessageUseCase: ManageSaveLaterMessageUseCase,
) : BaseViewModel<TopicUiState, TopicEffect>(TopicUiState()), TopicInteraction {

    private val topicArgs = TopicArgs(savedStateHandle)

    init {
        _state.update { it.copy(topicName = topicArgs.topicName) }
        getAllMessages()

    }

    override fun onClickBackButton() {
        sendUiEffect(TopicEffect.NavigationBack)
    }

    override fun openEmojisTile() {

    }

    override fun onMessageInputChanged(text: String) {
        _state.update { it.copy(messageInput = text) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onSendMessage(text: String) {
        viewModelScope.launch {
            val user = getCurrentMemberUseCase.invoke()
            val message = Message(
                id = "",
                senderId = user.id,
                senderFullName = user.name,
                senderAvatarUrl =user.imageUrl,
                messageContent = text,
                timestamp = Date.from(
                    LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()
                )
            )
            manageTopicMessagesUseCase.sendMessage(
                message = message,
                channelId = topicArgs.channelId.toString(),
                topicId = topicArgs.topicId.toString()
            )
        }
        _state.update { it.copy(messageInput = "") }
    }

    private fun getAllMessages() {
        viewModelScope.launch {
            tryToExecuteFlow({
                manageTopicMessagesUseCase.getMessages(
                    topicArgs.topicId.toString(),
                    topicArgs.channelId.toString()
                )
            }, ::onSuccessGetMessage, ::onError)
        }
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(error = throwable.message) }
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


    override fun onStartVoiceRecording() {

    }

    override fun onClickCamera() {

    }

    override fun onClickPhotoOrVideo(position: Int) {

    }

    override fun onAddReactionToMessage(messageId: Int) {

    }

    override fun onSaveMessage(message: MessageUiState) {
        viewModelScope.launch {
            manageSaveLaterMessageUseCase.addSavedLaterMessage(message.toEntity())
        }
    }

    override fun onGetNotification() {

    }

    override fun onPinMessage() {

    }

    override fun onOpenReactTile() {

    }

    override fun onClickReact(positive: Boolean, reactionUiState: ReactionUiState) {

    }

}