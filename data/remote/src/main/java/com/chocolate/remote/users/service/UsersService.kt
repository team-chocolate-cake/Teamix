package com.chocolate.remote.users.service

import com.chocolate.remote.users.request.CreateUserGroupSubgroupRequest
import com.chocolate.remote.users.request.CreateUserRequest
import com.chocolate.remote.users.request.ProfileData
import com.chocolate.remote.users.request.SettingsRequest
import com.chocolate.remote.users.request.StatusUpdate
import com.chocolate.remote.users.request.StatusUpdateRequest
import com.chocolate.remote.users.request.TypingStatusRequest
import com.chocolate.remote.users.request.UpdateInfo
import com.chocolate.remote.users.request.UpdateUserGroupMembersRequest
import com.chocolate.remote.users.request.UserGroupCreationRequest
import com.chocolate.remote.users.request.UserGroupUpdateRequest
import com.chocolate.remote.users.response.AlertWordsDTO
import com.chocolate.remote.users.response.CreateUserDTO
import com.chocolate.remote.users.response.OwnerUserDTO
import com.chocolate.remote.users.response.ResponseStateDTO
import com.chocolate.remote.users.response.SubgroupsOfUserGroupDTO
import com.chocolate.remote.users.response.UserAttachmentsDTO
import com.chocolate.remote.users.response.UserGroupMembersDTO
import com.chocolate.remote.users.response.UserGroupsDTO
import com.chocolate.remote.users.response.UserMembershipStateDTO
import com.chocolate.remote.users.response.UserDTO
import com.chocolate.remote.users.response.UserSettingsDTO
import com.chocolate.remote.users.response.UserStateDTO
import com.chocolate.remote.users.response.UsersDTO
import com.chocolate.remote.users.response.UsersStateDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


interface UserService {
    @GET("get-all-users")
    suspend fun getAllUsers(
        @Query("client_gravatar") clientGravatar: Boolean? = null,
        @Query("include_custom_profile_fields") includeCustomProfileFields: Boolean? = null
    ): Response<List<UsersDTO>>

    @GET("get-own-user")
    suspend fun getOwnUser(): Response<OwnerUserDTO>

    @GET("get-user/{id}")
    suspend fun getUserById(
        @Path("user_id") userId: Int,
        @Query("client_gravatar") clientGravatar: Boolean? = null,
        @Query("include_custom_profile_fields") includeCustomProfileFields: Boolean? = null
    ): Response<UserDTO>

    @GET("users/{email}")
    suspend fun getUserByEmail(
        @Path("email") email: String,
        @Query("client_gravatar") clientGravatar: Boolean? = null,
        @Query("include_custom_profile_fields") includeCustomProfileFields: Boolean? = null
    ): Response<UserDTO>

    @PATCH("users/{id}")
    suspend fun updateUserById(
        @Path("user_id") id: Int,
        @Query("full_name") fullName: String? = null,
        @Query("role") role: Int? = null,
        @Query("profile_data") profileData: List<ProfileData>? = null
    ): Response<ResponseStateDTO>

    @POST("users/me/status")
    suspend fun updateUserStatus(@Query("status") status: String): Response<ResponseStateDTO>

    @POST("create-user")
    suspend fun createUser(
        @Query("name") name: String,
        @Query("email") email: String
    ): Response<CreateUserDTO>

    @DELETE("users/{id}")
    suspend fun deactivateUser(@Path("id") id: Int): Response<ResponseStateDTO>

    @POST("users/{id}/reactivate")
    suspend fun reactivateUser(@Path("id") id: Int): Response<ResponseStateDTO>

    @DELETE("users/me")
    suspend fun deactivateOwnUser(): Response<ResponseStateDTO>

    @POST("typing")
    suspend fun setTypingStatus(@Query("typing") typing: Boolean): Response<ResponseStateDTO>

    @GET("users/{email}/presence")
    suspend fun getUserPresence(@Path("email") email: String): Response<UserStateDTO>

    @GET("realm/presence")
    suspend fun getRealmPresence(): Response<List<UsersStateDTO>>

    @GET("attachments")
    suspend fun getAttachments(): Response<List<UserAttachmentsDTO>>

    @DELETE("attachments/{attachment_id}")
    suspend fun deleteAttachment(@Path("attachment_id") attachmentId: Int): Response<ResponseStateDTO>

    @PATCH("settings")
    suspend fun updateSettings(@Query("settings") settings: SettingsRequest): Response<UserSettingsDTO>

    @GET("user-groups")
    suspend fun getUserGroups(): Response<List<UserGroupsDTO>>

    @POST("create-user-group")
    suspend fun createUserGroup(
        @Query("name") name: String,
        @Query("description") description: String
    ): Response<ResponseStateDTO>

    @PATCH("user-groups/{id}")
    suspend fun updateUserGroup(
        @Path("id") id: Int,
        @Query("name") name: String,
        @Query("description") description: String
    ): Response<ResponseStateDTO>

    @DELETE("user-groups/{id}")
    suspend fun removeUserGroup(@Path("id") id: Int): Response<ResponseStateDTO>

    @PUT("user-groups/{id}/members")
    suspend fun updateUserGroupMembers(
        @Path("id") id: Int,
        @Query("add") add: List<Int>,
        @Query("delete") delete: List<Int>
    ): Response<ResponseStateDTO>

    @POST("user_groups/{id}/subgroups")
    suspend fun createUserGroupSubgroups(
        @Path("id") id: Int,
        @Query("name") name: String,
        @Query("description") description: String,
        @Query("members") members: List<Int>
    ): Response<SubgroupsOfUserGroupDTO>

    @GET("user_groups/{groupId}/members/{userId}")
    suspend fun getUserMembership(
        @Path("groupId") groupId: Int,
        @Path("userId") userId: Int,
        @Query("direct_member_only") directMemberOnly: Boolean
    ): Response<UserMembershipStateDTO>

    @GET("user_groups/{groupId}/memberships")
    suspend fun getUserGroupMemberships(
        @Path("groupId") groupId: Int,
        @Query("direct_member_only") directMemberOnly: Boolean
    ): Response<List<UserMembershipStateDTO>>

    @GET("user_groups/{id}/subgroups")
    suspend fun getSubgroupsOfUserGroup(@Path("id") id: Int): Response<List<SubgroupsOfUserGroupDTO>>

    @GET("users/me/alert_words")
    suspend fun getAlertWords(): Response<AlertWordsDTO>

    @POST("users/me/alert_words")
    suspend fun addAlertWords(@Query("words") words: List<String>): Response<AlertWordsDTO>

    @DELETE("users/me/alert_words")
    suspend fun removeAlertWords(@Query("words") words: List<String>): Response<AlertWordsDTO>
}