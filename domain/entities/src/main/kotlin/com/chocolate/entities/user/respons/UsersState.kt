package com.chocolate.entities.user.respons

data class UsersState (
    val presences: Presences?,
    val serverTimestamp: Double?
)

data class Presences(
    val iagoZulipCom: IagoZulipCom?
)