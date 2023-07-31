package com.chocolate.repository.dto.users.response

import com.google.gson.annotations.SerializedName

data class UsersStateDto(
    @SerializedName("msg")
    val message: String?,
    @SerializedName("presences")
    val presences: Presences?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("server_timestamp")
    val serverTimestamp: Double?
) {
    data class Presences(
        @SerializedName("iago@zulip.com")
        val iagozulipCom: IagozulipCom?
    ) {
        data class IagozulipCom(
            @SerializedName("aggregated")
            val aggregated: Aggregated?,
            @SerializedName("website")
            val website: Website?
        ) {
            data class Aggregated(
                @SerializedName("client")
                val client: String?,
                @SerializedName("status")
                val status: String?,
                @SerializedName("timestamp")
                val timestamp: Int?
            )

            data class Website(
                @SerializedName("client")
                val client: String?,
                @SerializedName("pushable")
                val pushable: Boolean?,
                @SerializedName("status")
                val status: String?,
                @SerializedName("timestamp")
                val timestamp: Int?
            )
        }
    }
}
