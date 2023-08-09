package com.chocolate.repository.mappers.channel_mappers

import com.chocolate.entities.channel_models.ChannelDetails
import com.chocolate.entities.channel_models.ChannelId
import com.chocolate.entities.channel_models.ChannelSubscribers
import com.chocolate.entities.channel_models.DefaultChannelModel
import com.chocolate.entities.channel_models.StreamItem
import com.chocolate.entities.channel_models.SubscribeToStream
import com.chocolate.entities.channel_models.SubscriptionSettingsUpdate
import com.chocolate.entities.channel_models.SubscriptionStatus
import com.chocolate.entities.channel_models.TopicItem
import com.chocolate.entities.channel_models.Topics
import com.chocolate.repository.dto.remote.channels.response.AllSubscribersDto
import com.chocolate.repository.dto.remote.channels.response.DefaultStreamDto
import com.chocolate.repository.dto.remote.channels.response.StreamDto
import com.chocolate.repository.dto.remote.channels.response.StreamsIdDto
import com.chocolate.repository.dto.remote.channels.response.StreamsItem
import com.chocolate.repository.dto.remote.channels.response.SubscribeToStreamDto
import com.chocolate.repository.dto.remote.channels.response.SubscriptionSettingsDto
import com.chocolate.repository.dto.remote.channels.response.SubscriptionStatusDto
import com.chocolate.repository.dto.remote.channels.response.SubscriptionsItemDto
import com.chocolate.repository.dto.remote.channels.response.TopicsInStreamDto
import com.chocolate.repository.dto.remote.channels.response.TopicsItemDto
import com.chocolate.repository.dto.remote.channels.response.UnsubscribeFromStreamDto

fun SubscriptionsItemDto.toStreamInfo(): StreamItem {
    return StreamItem(
        streamId = streamId ?: 0,
        streamName = name ?: "",
        description = description ?: "",
        invitationONly = inviteOnly ?: false,
    )
}


fun SubscribeToStreamDto.toSubscribeToStream(): SubscribeToStream {
    return if (result.equals("success")) {
        if (unauthorized?.get(0).equals("private_stream"))
            SubscribeToStream(message = unauthorized?.get(0) ?: "")
        else
            SubscribeToStream(message = result ?: "")
    } else {
        SubscribeToStream(message = message ?: "")
    }
}


fun UnsubscribeFromStreamDto.toUnsubscribeFromStream(): DefaultChannelModel {
    return if (result.equals("success")) DefaultChannelModel(result ?: "")
    else
        DefaultChannelModel(message ?: "")
}

fun SubscriptionStatusDto.toSubscriptionStatus(): SubscriptionStatus {
    return SubscriptionStatus(isSubscribed ?: false)
}

fun AllSubscribersDto.toChannelSubscribers(): ChannelSubscribers {
    return ChannelSubscribers(
        subscribers ?: emptyList()
    )
}

fun SubscriptionSettingsDto.toSubscriptionSettingsUpdate(): SubscriptionSettingsUpdate {
    return SubscriptionSettingsUpdate(result ?: "", ignoredParametersUnsupported ?: emptyList())
}

fun StreamsItem?.toStreamItem(): StreamItem {
    return StreamItem(
        streamId = this?.streamId ?: 0,
        streamName = this?.name ?: "",
        description = this?.description ?: "",
        invitationONly = this?.inviteOnly ?: false,
    )
}


fun StreamDto.toChannelDetails(): ChannelDetails {
    return ChannelDetails(
        firstMessageId ?: 0,
        streamPostPolicy ?: 0,
        isWebPublic ?: false,
        renderedDescription ?: "",
        streamId ?: 0,
        name ?: "",
        description ?: "",
        historyPublicToSubscribers ?: true,
        isAnnouncementOnly ?: false,
        messageRetentionDays!!,
        canRemoveSubscribersGroupId ?: 0,
        inviteOnly ?: false

    )
}

fun StreamsIdDto.toChannelId(): ChannelId {
    return ChannelId(streamId ?: 0)
}

fun DefaultStreamDto.toChannelDefault(): DefaultChannelModel {
    return DefaultChannelModel(result ?: "")
}

fun TopicsItemDto.toTopicItem(): TopicItem {
    return TopicItem(name ?: "", maxId ?: 0)
}

fun TopicsInStreamDto.toTopics(): Topics {
    val topic = topics?.map { it.toTopicItem() }
    return Topics(topic ?: emptyList())
}