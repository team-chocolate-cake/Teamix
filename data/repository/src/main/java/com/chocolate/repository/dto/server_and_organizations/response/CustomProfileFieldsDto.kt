package com.chocolate.repository.dto.server_and_organizations.response


import com.google.gson.annotations.SerializedName

data class CustomProfileFieldsDto(
    @SerializedName("custom_fields")
    val customFields: List<CustomField>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("result")
    val result: String
) {
    data class CustomField(
        @SerializedName("display_in_profile_summary")
        val displayInProfileSummary: Boolean,
        @SerializedName("field_data")
        val fieldData: String,
        @SerializedName("hint")
        val hint: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("order")
        val order: Int,
        @SerializedName("type")
        val type: Int
    )
}