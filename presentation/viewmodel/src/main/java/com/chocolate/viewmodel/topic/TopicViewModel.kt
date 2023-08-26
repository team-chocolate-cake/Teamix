package com.chocolate.viewmodel.topic

import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopicViewModel @Inject constructor(
) : BaseViewModel<TopicUiState, TopicEffect>(TopicUiState()), TopicInteraction {
    override fun onClickBackButton() {
        sendUiEffect(TopicEffect.NavigationBack)
    }

    override fun openEmojisTile() {

    }

    override fun onMessageInputChanged(text: String) {

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