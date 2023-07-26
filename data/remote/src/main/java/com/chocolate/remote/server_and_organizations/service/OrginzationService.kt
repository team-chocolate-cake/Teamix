package com.chocolate.remote.server_and_organizations.service

import com.chocolate.remote.server_and_organizations.requestsDto.AddLinkifiersOrCodePlayGround
import com.chocolate.remote.server_and_organizations.requestsDto.CustomEmoji
import com.chocolate.remote.server_and_organizations.requestsDto.CustomProfileFieldsDto
import com.chocolate.remote.server_and_organizations.requestsDto.LinkifiersDto
import com.chocolate.remote.server_and_organizations.requestsDto.ServerSettingsDto
import com.chocolate.remote.server_and_organizations.requestsDto.UpdateOrRemove
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path


interface OrginizationService {

    @GET("server_settings")
    suspend fun getServiceSettings(): ServerSettingsDto

    @GET("realm/linkifiers")
    suspend fun getLinkifiers(): LinkifiersDto

    @POST("realm/filters")
    suspend fun AddLinkifiers(@Body addLinkifiers: AddLinkifiers): AddLinkifiersOrCodePlayGround

    @PATCH("realm/filters/{filter_id}")
    suspend fun updateLinkifiers(filterId: Int, @Body updateLinkifiers: AddLinkifiers)

    //remove linkifiers

    @POST("realm/playgrounds")
    suspend fun addCodePlayGround(@Body codePlayGrounds: CodePlayGrounds): AddLinkifiersOrCodePlayGround

    @DELETE("realm/playgrounds/{playground_id}")
    suspend fun deleteCodePlayGround(playGroundId: Int)

    @GET("realm/emoji")
    suspend fun getAllCustomEmojis(): CustomEmoji

    @POST("realm/emoji/{emoji_name}")
    suspend fun addCustomEmoji(emojiName: String)

    @DELETE("emoji/{emoji_name}")
    suspend fun deactivateCustomEmoji(emojiName: String)

    @GET("profile_fields")
    suspend fun getAllCustomProfileFields(): CustomProfileFieldsDto

//    @PATCH("profile_fields")
//    suspend fun reorderCustomProfileFields():UpdateOrRemove

}