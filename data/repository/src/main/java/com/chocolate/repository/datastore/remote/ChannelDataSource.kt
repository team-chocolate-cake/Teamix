package com.chocolate.repository.datastore.remote

import com.chocolate.repository.model.dto.channels.ChannelDto
import kotlinx.coroutines.flow.Flow

interface ChannelDataSource {
    suspend fun createChannel(
        channelName: String,
        usersId: List<Int>,
        isPrivate: Boolean,
        description: String?,
    )

    fun getChannels(): Flow<List<ChannelDto>>
}