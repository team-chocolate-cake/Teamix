package com.chocolate.entities.user

data class UsersState (
    val presences: Presences,
    val serverTimestamp: Double
)

data class Presences(
    val iagoZulipCom: IagoZulipCom
)