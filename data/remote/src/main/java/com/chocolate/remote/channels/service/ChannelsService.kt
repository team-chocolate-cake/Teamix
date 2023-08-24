package com.chocolate.remote.channels.service


import com.chocolate.repository.model.dto.channels.response.AllStreamsDto
import com.chocolate.repository.model.dto.channels.response.AllSubscribersDto
import com.chocolate.repository.model.dto.channels.response.DefaultStreamDto
import com.chocolate.repository.model.dto.channels.response.StreamsByIdDto
import com.chocolate.repository.model.dto.channels.response.StreamsIdDto
import com.chocolate.repository.model.dto.channels.response.SubscribeToStreamDto
import com.chocolate.repository.model.dto.channels.response.SubscribedStreamDto
import com.chocolate.repository.model.dto.channels.response.SubscriptionStatusDto
import com.chocolate.repository.model.dto.channels.response.TopicsInStreamDto
import com.chocolate.repository.model.dto.channels.response.UnsubscribeFromStreamDto
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ChannelsService {
    @GET("users/me/subscriptions")
    suspend fun getSubscribedChannels(): Response<SubscribedStreamDto>

    @POST("users/me/subscriptions")
    suspend fun subscribeToChannels(
        @Query("subscriptions") channelsName: String,
        @Query("principals") usersId: List<Int>,
    ): Response<SubscribeToStreamDto>

    @DELETE("users/me/subscriptions")
    suspend fun unsubscribeFromChannels(
        @Query("subscriptions") channelsName: List<String>,
    ): Response<UnsubscribeFromStreamDto>

    @GET("users/{user_id}/subscriptions/{stream_id}")
    suspend fun getSubscriptionStatus(
        @Path("user_id") userId: Int,
        @Path("stream_id") channelId: Int,
    ): Response<SubscriptionStatusDto>

    @GET("streams/{stream_id}/members")
    suspend fun getSubscribersInChannel(
        @Path("stream_id") channelId: Int
    ): Response<AllSubscribersDto>

    @GET("streams")
    suspend fun getChannels(): Response<AllStreamsDto>

    @GET("streams/{stream_id}")
    suspend fun getChannelById(
        @Path("stream_id") channelId: Int
    ): Response<StreamsByIdDto>

    @GET("get_stream_id")
    suspend fun getChannelIdByName(
        @Query("stream") channelName: String
    ): Response<StreamsIdDto>

    @PATCH("streams/{stream_id}")
    suspend fun updateChannel(
        @Path("stream_id") channelId: Int,
        @Query("description") description: String? = null,
        @Query("new_name") newName: String? = null,
        @Query("is_private") isPrivate: Boolean? = null,
        @Query("is_web_public") isWebPublic: Boolean? = null,
        @Query("history_public_to_subscribers") historyPublicToSubscribers: Boolean? = null,
        @Query("stream_post_policy") streamPostPolicy: Int? = null,
        @Query("message_retention_days") messageRetentionDays: String? = null,
        @Query("can_remove_subscribers_group_id") canRemoveSubscribersGroupId: Int? = null,
    ): Response<DefaultStreamDto>

    @DELETE("streams/{stream_id}")
    suspend fun archiveChannel(
        @Path("stream_id") channelId: Int
    ): Response<DefaultStreamDto>

    @GET("users/me/{stream_id}/topics")
    suspend fun getTopicsInChannel(
        @Path("stream_id") channelId: Int
    ): Response<TopicsInStreamDto>

    @PATCH("users/me/subscriptions/muted_topics")
    suspend fun setTopicMuting(
        @Query("topic") topicName: String,
        @Query("op") status: String,
        @Query("stream_id") channelId: Int?,
    ): Response<DefaultStreamDto>

    @POST("user_topics")
    suspend fun updatePersonalPreferenceTopic(
        @Query("stream_id") streamId: Int,
        @Query("topic") topic: String,
        @Query("visibility_policy") visibilityPolicy: Int,
    ): Response<DefaultStreamDto>

    @POST("streams/{stream_id}/delete_topic")
    suspend fun deleteTopic(
        @Path("stream_id") channelId: Int,
        @Query("topic_name") topicName: String
    ): Response<DefaultStreamDto>

    @POST("default_streams")
    suspend fun addDefaultChannel(
        @Query("stream_id") channelId: Int,
    ): Response<DefaultStreamDto>

    @DELETE("default_streams")
    suspend fun deleteDefaultChannel(
        @Query("stream_id") channelId: Int
    ): Response<DefaultStreamDto>
}