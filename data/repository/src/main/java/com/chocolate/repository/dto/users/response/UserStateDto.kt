package com.chocolate.repository.dto.users.response


import com.google.gson.annotations.SerializedName

data class UserStateDto(
    @SerializedName("msg")
    val message: String?,
    @SerializedName("presence")
    val presence: Presence?,
    @SerializedName("result")
    val result: String?
) {
    data class Presence(
        @SerializedName("aggregated")
        val aggregated: Aggregated?,
        @SerializedName("website")
        val website: Website?
    ) {
        data class Aggregated(
            @SerializedName("status")
            val status: String?,
            @SerializedName("timestamp")
            val timestamp: Int?
        )

        data class Website(
            @SerializedName("status")
            val status: String?,
            @SerializedName("timestamp")
            val timestamp: Int?
        )
    }
}
