package com.chocolate.remote.server_and_organizations.service

import com.chocolate.repository.dto.server_and_organizations.response.AddLinkifiersOrCodePlayGroundDto
import com.chocolate.repository.dto.server_and_organizations.response.CustomEmojiDto
import com.chocolate.repository.dto.server_and_organizations.response.CustomProfileFieldsDto
import com.chocolate.repository.dto.server_and_organizations.response.LinkifiersDto
import com.chocolate.repository.dto.server_and_organizations.response.ServerSettingsDto
import com.chocolate.repository.dto.server_and_organizations.response.UpdateOrRemoveDto
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface OrganizationService {

    @GET("server_settings")
    suspend fun getServerSettings(): Response<ServerSettingsDto>

    @GET("realm/linkifiers")
    suspend fun getLinkifiers(): Response<LinkifiersDto>

    @POST("realm/filters")
    suspend fun addLinkifiers(
        @Query("pattern") pattern: String,
        @Query("url_template") url: String
    ): Response<AddLinkifiersOrCodePlayGroundDto>

    @PATCH("realm/filters/{filter_id}")
    suspend fun updateLinkifiers(
        @Path("filter_id") filterId: Int,
        @Query("pattern") pattern: String,
        @Query("url_template") url: String
    ): Response<UpdateOrRemoveDto>

    @DELETE("filters/{filter_id}")
    suspend fun deleteLinkifiers(@Path("filter_id") filterId: Int): Response<UpdateOrRemoveDto>

    @POST("realm/playgrounds")
    suspend fun addCodePlayGround(
        @Query("name") name: String,
        @Query("pygments_language") language: String,
        @Query("url_prefix") url: String,
    ): Response<AddLinkifiersOrCodePlayGroundDto>

    @DELETE("realm/playgrounds/{playground_id}")
    suspend fun deleteCodePlayground(@Path("playground_id") playGroundId: Int): Response<UpdateOrRemoveDto>

    @GET("realm/emoji")
    suspend fun getAllCustomEmojis(): Response<CustomEmojiDto>

    @POST("realm/emoji/{emoji_name}")
    suspend fun addCustomEmoji(@Path("emoji_name") emojiName: String): Response<UpdateOrRemoveDto>

    @DELETE("realm/emoji/{emoji_name}")
    suspend fun deactivateCustomEmoji(@Path("emoji_name") emojiName: String): Response<UpdateOrRemoveDto>

    @GET("realm/profile_fields")
    suspend fun getAllCustomProfileFields(): Response<CustomProfileFieldsDto>

    @PATCH("realm/profile_fields")
    suspend fun reorderCustomProfileFields(
        @Query("order") order: String
    ): Response<UpdateOrRemoveDto>

    @POST("realm/profile_fields")
    suspend fun createCustomProfileField(
        @Query("name") name: String = "",
        @Query("hint") hint: String = "",
        @Query("field_type") fieldType: Int,
    ): Response<AddLinkifiersOrCodePlayGroundDto>

}