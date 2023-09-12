package com.chocolate.viewmodel.topic



interface TopicInteraction {
    fun onClickBackButton()
    fun openEmojisTile()
    fun onMessageInputChanged(text: String)
    fun onSendMessage(text: String)
    fun onStartVoiceRecording()
    fun onClickCamera()
    fun onClickPhotoOrVideo(position: Int)
    fun onAddReactionToMessage(messageId: Int)
    fun onSaveMessage()
    fun onGetNotification()
    fun onPinMessage()
    fun onOpenReactTile()
    fun onClickReact(positive: Boolean, reactionUiState: ReactionUiState)

}