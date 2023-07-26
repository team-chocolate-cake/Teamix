package com.chocolate.remote.users.response


import com.google.gson.annotations.SerializedName

data class UserStateDTO(
    @SerializedName("msg")
    val msg: String,
    @SerializedName("presence")
    val presence: Presence,
    @SerializedName("result")
    val result: String
) {
    data class Presence(
        @SerializedName("aggregated")
        val aggregated: Aggregated,
        @SerializedName("website")
        val website: Website
    ) {
        data class Aggregated(
            @SerializedName("status")
            val status: String,
            @SerializedName("timestamp")
            val timestamp: Int
        )

        data class Website(
            @SerializedName("status")
            val status: String,
            @SerializedName("timestamp")
            val timestamp: Int
        )
    }
}