package com.chocolate.repository.Mappers.ChannelMappers

import com.chocolate.entities.ChannelModels.ChannelDetails
import com.chocolate.entities.ChannelModels.ChannelId
import com.chocolate.entities.ChannelModels.ChannelSubscribers
import com.chocolate.entities.ChannelModels.StreamItem
import com.chocolate.entities.ChannelModels.SubscribeToStream
import com.chocolate.entities.ChannelModels.SubscriptionSettingsUpdate
import com.chocolate.entities.ChannelModels.SubscriptionStatus
import com.chocolate.entities.ChannelModels.DefaultChannelModel
import com.chocolate.entities.ChannelModels.TopicItem
import com.chocolate.entities.ChannelModels.Topics
import com.chocolate.repository.dto.channels.response.AllSubscribersDto
import com.chocolate.repository.dto.channels.response.DefaultStreamDto
import com.chocolate.repository.dto.channels.response.Stream
import com.chocolate.repository.dto.channels.response.StreamsIdDto
import com.chocolate.repository.dto.channels.response.StreamsItem
import com.chocolate.repository.dto.channels.response.SubscribeToStreamDto
import com.chocolate.repository.dto.channels.response.SubscriptionSettingsDto
import com.chocolate.repository.dto.channels.response.SubscriptionStatusDto
import com.chocolate.repository.dto.channels.response.SubscriptionsItem
import com.chocolate.repository.dto.channels.response.TopicsInStreamDto
import com.chocolate.repository.dto.channels.response.TopicsItem
import com.chocolate.repository.dto.channels.response.UnsubscribeFromStreamDto

fun SubscriptionsItem.toStreamInfo(): StreamItem {
    return StreamItem(
        streamId = streamId,
        streamName = name,
        description = description,
        invitationONly = inviteOnly,
    )
}


fun SubscribeToStreamDto.toSubscribeToStream(): SubscribeToStream {
    return if (result.equals("success")) {
        if (unauthorized?.get(0).equals("private_stream"))
            SubscribeToStream(message = unauthorized?.get(0))
        else
            SubscribeToStream(message = result)
    } else {
        SubscribeToStream(message = message)
    }
}


fun UnsubscribeFromStreamDto.toUnsubscribeFromStream(): DefaultChannelModel {
    return if (result.equals("success")) DefaultChannelModel(result)
    else
        DefaultChannelModel(message)
}

fun SubscriptionStatusDto.toSubscriptionStatus(): SubscriptionStatus {
    return SubscriptionStatus(isSubscribed)
}

fun AllSubscribersDto.toChannelSubscribers(): ChannelSubscribers {
    return ChannelSubscribers(
        subscribers ?: emptyList()
    )
}

fun SubscriptionSettingsDto.toSubscriptionSettingsUpdate(): SubscriptionSettingsUpdate {
    return SubscriptionSettingsUpdate(result, ignoredParametersUnsupported)
}

fun StreamsItem?.toStreamItem(): StreamItem {
    return StreamItem(
        streamId = this?.streamId,
        streamName = this?.name,
        description = this?.description,
        invitationONly = this?.inviteOnly,
    )
}


fun Stream.toChannelDetails(): ChannelDetails {
    return ChannelDetails(
        firstMessageId,
        streamPostPolicy,
        isWebPublic,
        renderedDescription,
        streamId,
        name,
        description,
        historyPublicToSubscribers
    )
}


fun StreamsIdDto.toChannelId(): ChannelId {
    return ChannelId(streamId)
}

fun DefaultStreamDto.toChannelDefault(): DefaultChannelModel {
    return DefaultChannelModel(result)
}

fun TopicsItem.toTopicItem(): TopicItem {
    return TopicItem(name, maxId)
}

fun TopicsInStreamDto.toTopics(): Topics {
    return Topics(topics?.map { it?.toTopicItem() })
}