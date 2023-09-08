package com.chocolate.viewmodel.topic

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.chocolate.entities.messages.Message
import com.chocolate.usecases.message.ManageChannelMessages
import com.chocolate.usecases.user.GetCurrentUserDataUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val manageChannelMessages: ManageChannelMessages,
    private val getCurrentUserDataUseCase: GetCurrentUserDataUseCase
) : BaseViewModel<TopicUiState, TopicEffect>(TopicUiState()), TopicInteraction {

    private val topicArgs = TopicArgs(savedStateHandle)
    //private val channelArgs = ChannelArgs(savedStateHandle)

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

    override fun onSendMessage(text: String) {
        Log.i("SHowALLL",state.value.messages.toString())
        viewModelScope.launch {
            val id=getCurrentUserDataUseCase.invoke().id
            val name=getCurrentUserDataUseCase.invoke().fullName
            val imageUrl=getCurrentUserDataUseCase.invoke().imageUrl
            manageChannelMessages.sendMessage(channelId =topicArgs.topicId.toString(), text = text,
                userId= id.toString(), senderName = name,senderImage=imageUrl)
        }
    }

    private fun getAllMessages() {
        viewModelScope.launch {
            tryToExecuteFlow({manageChannelMessages.getMessages(topicArgs.topicId.toString())},::onSuccessGetMessage,::onError)
        /*    manageChannelMessages.getMessages(topicArgs.topicId.toString()).collectLatest { messages ->
                messages.map {
                    val id = getCurrentUserDataUseCase.invoke().id
                    val isMyMessage = it.senderId==id
                    Log.i("TEstAbood",isMyMessage.toString())
                   // Log.i("TEstIDD",channelArgs.channelId.toString())
                    _state.update {
                        it.copy(
                            messages = messages.toUiState(false)
                        )
                    }
                }
            }*/
        }
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(error = throwable.message) }
    }

    private suspend fun onSuccessGetMessage(flow: Flow<List<Message>>) {
        flow.collectLatest {messages ->
            messages.map {
                val id = getCurrentUserDataUseCase.invoke().id
                val isMyMessage = it.senderId==id
                Log.i("TEstAbood",isMyMessage.toString())
                // Log.i("TEstIDD",channelArgs.channelId.toString())
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

    override fun onSaveMessage() {

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