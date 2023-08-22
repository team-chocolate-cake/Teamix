package com.chocolate.viewmodel.topic

sealed interface TopicEffect{
    object NavigationBack: TopicEffect
}