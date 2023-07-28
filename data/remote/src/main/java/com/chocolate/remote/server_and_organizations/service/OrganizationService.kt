package com.chocolate.remote.server_and_organizations.service

import com.chocolate.remote.server_and_organizations.requests.AddLinkifiers
import com.chocolate.remote.server_and_organizations.requests.CodePlayGrounds
import com.chocolate.remote.server_and_organizations.requests.ProfileFieldOrder
import com.chocolate.remote.server_and_organizations.requests.ProfileFieldRequest
import com.chocolate.remote.server_and_organizations.response.AddLinkifiersOrCodePlayGroundDto
import com.chocolate.remote.server_and_organizations.response.CustomEmojiDto
import com.chocolate.remote.server_and_organizations.response.CustomProfileFieldsDto
import com.chocolate.remote.server_and_organizations.response.LinkifiersDto
import com.chocolate.remote.server_and_organizations.response.ServerSettingsDto
import com.chocolate.remote.server_and_organizations.response.UpdateOrRemove
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface OrganizationService {

    @GET("server_settings")
    suspend fun getServiceSettings(): Response<ServerSettingsDto>

    @GET("realm/linkifiers")
    suspend fun getLinkifiers(): Response<LinkifiersDto>

    @POST("realm/filters")
    suspend fun addLinkifiers(@Body addLinkifiers: AddLinkifiers):
            Response<AddLinkifiersOrCodePlayGroundDto>

    @PATCH("realm/filters/{filter_id}")
    suspend fun updateLinkifiers(
        filterId: Int,
        @Body updateLinkifiers: AddLinkifiers
    ): Response<UpdateOrRemove>

    @DELETE("filters/{filter_id}")
    suspend fun deleteLinkifiers(@Path("filter_id") filterId: Int): Response<UpdateOrRemove>

    @POST("realm/playgrounds")
    suspend fun addCodePlayGround(@Body codePlayGrounds: CodePlayGrounds):
            Response<AddLinkifiersOrCodePlayGroundDto>

    @DELETE("realm/playgrounds/{playground_id}")
    suspend fun deleteCodePlayground(@Path("playground_id") playGroundId: Int)

    @GET("realm/emoji")
    suspend fun getAllCustomEmojis(): Response<CustomEmojiDto>

    @POST("realm/emoji/{emoji_name}")
    suspend fun addCustomEmoji(@Path("emoji_name") emojiName: String): Response<UpdateOrRemove>

    @DELETE("realm/emoji/{emoji_name}")
    suspend fun deactivateCustomEmoji(emojiName: String): Response<UpdateOrRemove>

    @GET("realm/profile_fields")
    suspend fun getAllCustomProfileFields(): Response<CustomProfileFieldsDto>

    @PATCH("realm/profile_fields")
    suspend fun reorderCustomProfileFields(@Body profileFieldOrder: ProfileFieldOrder):
            Response<UpdateOrRemove>

    @POST("realm/profile_fields")
    suspend fun createCustomProfileField(@Body profileFieldRequest: ProfileFieldRequest):
            Response<AddLinkifiersOrCodePlayGroundDto>

}