package com.chocolate.repository.datastore.realtime

import com.chocolate.repository.datastore.realtime.model.ChannelDto
import kotlinx.coroutines.flow.Flow

interface RealTimeDataSource {
    suspend fun createChannel(
        channelName: String,
        usersId: List<Int>,
        isPrivate: Boolean,
        description: String?,
    )

    fun getChannels(): Flow<List<ChannelDto>>
}