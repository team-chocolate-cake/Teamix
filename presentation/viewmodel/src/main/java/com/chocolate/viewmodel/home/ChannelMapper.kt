package com.chocolate.viewmodel.home

import com.chocolate.entities.channel.Channel
import com.chocolate.entities.channel.Topic
import com.chocolate.viewmodel.search.ChannelsUiState

@JvmName("channelToChannelUiState")
fun Channel.toUiState(): ChannelUiState =
    ChannelUiState(
        id,
        name,
        topics.toUiState(),
        isPrivate
    )

fun Channel.toChannelsUiState(): ChannelsUiState =
    ChannelsUiState(
        id,
        name,
        isPrivate = isPrivate
    )

@JvmName("channelsToChannelsUiState")
fun List<Channel>.toUiState(): List<ChannelUiState> = this.map { it.toUiState() }

@JvmName("topicToTopicUiState")
fun Topic.toUiState(): TopicUiState = TopicUiState(name, 5)

@JvmName("topicsToTopicsUiState")
fun List<Topic>.toUiState():List<TopicUiState> = this.map { it.toUiState() }

fun List<Channel>.toChannelsUiState(): List<ChannelsUiState> = this.map { it.toChannelsUiState() }