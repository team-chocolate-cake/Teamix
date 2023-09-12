package com.chocolate.viewmodel.DMChat

import androidx.lifecycle.SavedStateHandle
import com.chocolate.usecases.direct_message.GetAllMessagesInChatUseCase
import com.chocolate.viewmodel.base.BaseViewModel
import com.chocolate.viewmodel.topic.ReactionUiState
import com.chocolate.viewmodel.topic.TopicInteraction
import com.chocolate.viewmodel.topic.TopicUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DMChatViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<TopicUiState, Unit>(TopicUiState()) , TopicInteraction {

    private val dmChatArgs = DMChatArgs(savedStateHandle)
    init {
        _state.update {
            it.copy(
                topicName = dmChatArgs.memberName
            )
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

    override fun onSendMessage() {
        TODO("Not yet implemented")
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