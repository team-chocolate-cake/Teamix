package com.chocolate.repository.datastore.realtime.model

data class ChannelDto(
    val id: String ?=null,
    val name:String?=null,
    val usersId:List<Int>?=null,
    val description:String?=null,
    val isPrivate:Boolean?=null,
)
