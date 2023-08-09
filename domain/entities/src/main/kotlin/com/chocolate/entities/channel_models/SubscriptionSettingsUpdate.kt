package com.chocolate.entities.channel_models

data class SubscriptionSettingsUpdate(
    val message: String,
    val ignoredParametersUnsupported: List<String>,
)

