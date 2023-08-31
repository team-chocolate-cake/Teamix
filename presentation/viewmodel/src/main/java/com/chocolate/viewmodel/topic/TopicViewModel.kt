package com.chocolate.viewmodel.topic

import androidx.lifecycle.SavedStateHandle
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TopicViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<TopicUiState, TopicEffect>(TopicUiState()), TopicInteraction {

    private val topicArgs = TopicArgs(savedStateHandle)

    init { _state.update { it.copy(topicName = topicArgs.topicName) } }

    override fun onClickBackButton() {
        sendUiEffect(TopicEffect.NavigationBack)
    }

    override fun openEmojisTile() {

    }

    override fun onMessageInputChanged(text: String) {
        _state.update { it.copy(messageInput = text) }
    }

    override fun onSendMessage() {

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