package com.chocolate.repository.datastore.remote

import com.chocolate.repository.model.dto.channels.ChannelDto
import kotlinx.coroutines.flow.Flow

interface ChannelDataSource {
    suspend fun createChannel(
        channel:ChannelDto,
        organizationName:String
    )



   suspend fun  getChannelsInOrganizationByOrganizationName(organizationName: String):Flow<List<ChannelDto>?>


    // fun getChannels(): Flow<List<ChannelDto>>
//    suspend fun sendMessage(
//        text: String, userId: Int,
//        channel: Int,
//        senderName: String,
//        senderImage: String
//    )
//
//    suspend fun getMessages(channelId: Int): Flow<List<MessageDto>>

}