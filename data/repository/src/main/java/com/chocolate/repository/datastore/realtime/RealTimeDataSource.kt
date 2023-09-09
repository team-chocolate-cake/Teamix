package com.chocolate.repository.datastore.realtime

import com.chocolate.repository.datastore.realtime.model.ChannelDto
import com.chocolate.repository.datastore.realtime.model.MessageDto
import kotlinx.coroutines.flow.Flow

interface RealTimeDataSource {
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