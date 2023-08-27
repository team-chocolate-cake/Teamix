package com.chocolate.viewmodel.createChannel

interface CreateChannelInteraction {
    fun onCreateChannelClicked()
    fun onChannelNameTextChange(channelName: String)
    fun onChannelDescriptionChange(channelDescription: String?)
    fun onChannelStatusChange(newChannelStatus: ChannelStatus, isPrivate: Boolean)
    fun onClickRetry()
}