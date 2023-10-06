package com.chocolate.viewmodel.channel

interface ChannelInteraction {
    fun onClickSeeAll(channelId: Int, topicId: String, topicName: String)
    fun onAddTopicClick()
    fun onSaveTopic(topic: TopicState)

    fun onClickMeetingIcon()
}