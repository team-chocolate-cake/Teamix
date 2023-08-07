package com.chocolate.remote.channels.service


import com.chocolate.repository.dto.remote.channels.response.AllStreamsDto
import com.chocolate.repository.dto.remote.channels.response.AllSubscribersDto
import com.chocolate.repository.dto.remote.channels.response.DefaultStreamDto
import com.chocolate.repository.dto.remote.channels.response.StreamsByIdDto
import com.chocolate.repository.dto.remote.channels.response.StreamsIdDto
import com.chocolate.repository.dto.remote.channels.response.SubscribeToStreamDto
import com.chocolate.repository.dto.remote.channels.response.SubscribedStreamDto
import com.chocolate.repository.dto.remote.channels.response.SubscriptionSettingsDto
import com.chocolate.repository.dto.remote.channels.response.SubscriptionStatusDto
import com.chocolate.repository.dto.remote.channels.response.TopicsInStreamDto
import com.chocolate.repository.dto.remote.channels.response.UnsubscribeFromStreamDto
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ChannelsService {
    @GET("users/me/subscriptions")
    suspend fun getUserSubscriptions(
        @Query("include_subscribers") includeSubscribers: Boolean = false
    ): Response<SubscribedStreamDto>

    @POST("users/me/subscriptions")
    suspend fun addSubscribesToStream(
        @Query("subscriptions") subscribeToStream: String,
        @Query("principals") principals: List<String>? = null,
        @Query("authorization_errors_fatal") authorizationErrorsFatal: Boolean = true,
        @Query("announce") announce: Boolean = false,
        @Query("invite_only") inviteOnly: Boolean = false,
        @Query("is_web_public") isWebPublic: Boolean = false,
        @Query("history_public_to_subscribers") historyPublicToSubscribers: Boolean = false,
        @Query("stream_post_policy") streamPostPolicy: Int? = null,
        @Query("message_retention_days") messageRetentionDays: String? = null,
        @Query("can_remove_subscribers_group_id") canRemoveSubscribersGroupId: Int? = null,
    ): Response<SubscribeToStreamDto>

    @DELETE("users/me/subscriptions")
    suspend fun deleteSubscriberFromStream(
        @Query("subscriptions") subscriptions: String,
        @Query("principals") principals: List<String>? = null,
    ): Response<UnsubscribeFromStreamDto>

    @GET("users/{user_id}/subscriptions/{stream_id}")
    suspend fun getSubscriptionStatus(
        @Path("user_id") userId: Int,
        @Path("stream_id") streamId: Int,
    ): Response<SubscriptionStatusDto>

    @GET("streams/{stream_id}/members")
    suspend fun getAllSubscriber(
        @Path("stream_id") streamId: Int
    ): Response<AllSubscribersDto>

    @POST("users/me/subscriptions/properties")
    suspend fun updateSubscriptionSettings(
        @Query("subscription_data") subscriptionData: String
    ): Response<SubscriptionSettingsDto>

    @GET("streams")
    suspend fun getAllStreams(
        @Query("include_public") includePublic: Boolean = true,
        @Query("include_web_public") includeWebPublic: Boolean = false,
        @Query("include_subscribed") includeSubscribed: Boolean = true,
        @Query("include_all_active") includeAllActive: Boolean = false,
        @Query("include_default") includeDefault: Boolean = false,
        @Query("include_owner_subscribed") includeOwnerSubscribed: Boolean = false,
    ): Response<AllStreamsDto>

    @GET("streams/{stream_id}")
    suspend fun getStreamById(
        @Path("stream_id") streamId: Int
    ): Response<StreamsByIdDto>

    @GET("get_stream_id")
    suspend fun getStreamId(
        @Query("stream") stream: String
    ): Response<StreamsIdDto>

    @PATCH("streams/{stream_id}")
    suspend fun updateStream(
        @Path("stream_id") streamId: Int,
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
    suspend fun archiveStream(
        @Path("stream_id") streamId: Int
    ): Response<DefaultStreamDto>

    @GET("users/me/{stream_id}/topics")
    suspend fun getTopicsInStream(
        @Path("stream_id") streamId: Int
    ): Response<TopicsInStreamDto>

    @PATCH("users/me/subscriptions/muted_topics")
    suspend fun setTopicMuting(
        @Query("topic") topic: String,
        @Query("op") status: String,
        @Query("stream_id") streamId: Int? = null,
        @Query("stream") stream: String? = null,
    ): Response<DefaultStreamDto>

    @POST("user_topics")
    suspend fun updatePersonalPreferenceTopic(
        @Query("stream_id") streamId: Int,
        @Query("topic") topic: String,
        @Query("visibility_policy") visibilityPolicy: Int,
    ): Response<DefaultStreamDto>

    @POST("streams/{stream_id}/delete_topic")
    suspend fun deleteTopic(
        @Path("stream_id") streamId: Int,
        @Query("topic_name") topicName: String
    ): Response<DefaultStreamDto>

    @POST("default_streams")
    suspend fun addDefaultStream(
        @Query("stream_id") streamId: Int,
    ): Response<DefaultStreamDto>

    @DELETE("default_streams")
    suspend fun deleteDefaultStream(
        @Query("stream_id") streamId: Int
    ): Response<DefaultStreamDto>
}