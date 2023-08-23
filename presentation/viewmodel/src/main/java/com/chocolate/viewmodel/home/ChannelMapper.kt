package com.chocolate.viewmodel.home

import com.chocolate.entities.channel.Channel
import com.chocolate.entities.channel.Topic

@JvmName("channelToChannelUiState")
fun Channel.toUiState(): ChannelUiState =
    ChannelUiState(
        id,
        name,
        topics.toUiState(),
        false
    )

@JvmName("channelsToChannelsUiState")
fun List<Channel>.toUiState(): List<ChannelUiState> = this.map { it.toUiState() }

@JvmName("topicToTopicUiState")
fun Topic.toUiState(): TopicUiState = TopicUiState(name, 5)

@JvmName("topicsToTopicsUiState")
fun List<Topic>.toUiState():List<TopicUiState> = this.map { it.toUiState() }