package com.chocolate.entities.user

data class UserState(
    val presence: Presence,
)
data class Presence(
    val aggregated: Aggregated,
    val website: Website

)
