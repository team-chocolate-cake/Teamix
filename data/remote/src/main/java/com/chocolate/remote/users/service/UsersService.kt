package com.chocolate.remote.users.service


import com.chocolate.repository.dto.remote.users.request.ProfileData
import com.chocolate.repository.dto.remote.users.request.SettingsRequest
import com.chocolate.repository.dto.remote.users.response.AlertWordsDto
import com.chocolate.repository.dto.remote.users.response.CreateUserDto
import com.chocolate.repository.dto.remote.users.response.MuteUserResponseDto
import com.chocolate.repository.dto.remote.users.response.OwnerUserDto
import com.chocolate.repository.dto.remote.users.response.ResponseStateDto
import com.chocolate.repository.dto.remote.users.response.SubgroupsOfUserGroupDto
import com.chocolate.repository.dto.remote.users.response.UserAttachmentsDto
import com.chocolate.repository.dto.remote.users.response.UserDto
import com.chocolate.repository.dto.remote.users.response.UserGroupMembershipsDto
import com.chocolate.repository.dto.remote.users.response.UserGroupsDto
import com.chocolate.repository.dto.remote.users.response.UserMembershipStateDto
import com.chocolate.repository.dto.remote.users.response.UserSettingsDto
import com.chocolate.repository.dto.remote.users.response.UserStateDto
import com.chocolate.repository.dto.remote.users.response.UsersDto
import com.chocolate.repository.dto.remote.users.response.UsersStateDto
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UsersService {
    @GET("users")
    suspend fun getAllUsers(
        @Query("client_gravatar") clientGravatar: Boolean = true,
        @Query("include_custom_profile_fields") includeCustomProfileFields: Boolean = false
    ): Response<com.chocolate.repository.dto.remote.users.response.UsersDto>

    @GET("users/me")
    suspend fun getOwnUser(): Response<com.chocolate.repository.dto.remote.users.response.OwnerUserDto>

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") userId: Int,
        @Query("client_gravatar") clientGravatar: Boolean = true,
        @Query("include_custom_profile_fields") includeCustomProfileFields: Boolean = false
    ): Response<com.chocolate.repository.dto.remote.users.response.UserDto>

    @GET("users/{email}")
    suspend fun getUserByEmail(
        @Path("email") email: String,
        @Query("client_gravatar") clientGravatar: Boolean = true,
        @Query("include_custom_profile_fields") includeCustomProfileFields: Boolean = false
    ): Response<com.chocolate.repository.dto.remote.users.response.UserDto>

    @PATCH("users/{id}")
    suspend fun updateUserById(
        @Path("id") id: Int,
        @Query("full_name") fullName: String? = null,
        @Query("role") role: Int? = null,
        @Query("profile_data") profileData: List<com.chocolate.repository.dto.remote.users.request.ProfileData>? = null
    ): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto>

    @POST("users/me/status")
    suspend fun updateUserStatus(
        @Query("status_text") statusText: String? = null,
        @Query("away") away: Boolean? = null,
        @Query("emoji_name") emojiName: String? = null,
        @Query("emoji_code") emojiCode: String? = null,
        @Query("reaction_type") reactionType: String? = null
    ): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto>

    @POST("users")
    suspend fun createUser(
        @Query("email") email: String,
        @Query("password") password: String,
        @Query("full_name") fullName: String
    ): Response<com.chocolate.repository.dto.remote.users.response.CreateUserDto>

    @DELETE("users/{id}")
    suspend fun deactivateUser(@Path("id") id: Int): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto>

    @POST("users/{id}/reactivate")
    suspend fun reactivateUser(@Path("id") id: Int): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto>

    @DELETE("users/me")
    suspend fun deactivateOwnUser(): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto>

    @POST("typing")
    suspend fun setTypingStatus(
        @Query("op") op: String,
        @Query("to") to: String,
        @Query("type") type: String? = "direct",
        @Query("topic") topic: String? = null
    ): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto>

    @GET("users/{email}/presence")
    suspend fun getUserPresence(@Path("email") email: String): Response<com.chocolate.repository.dto.remote.users.response.UserStateDto>

    @GET("realm/presence")
    suspend fun getRealmPresence(): Response<com.chocolate.repository.dto.remote.users.response.UsersStateDto>

    @GET("attachments")
    suspend fun getAttachments(): Response<com.chocolate.repository.dto.remote.users.response.UserAttachmentsDto>

    @DELETE("attachments/{attachment_id}")
    suspend fun deleteAttachment(@Path("attachment_id") attachmentId: Int): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto>

    @PATCH("settings")
    suspend fun updateSettings(@Query("settings") settings: com.chocolate.repository.dto.remote.users.request.SettingsRequest): Response<com.chocolate.repository.dto.remote.users.response.UserSettingsDto>

    @GET("user_groups")
    suspend fun getUserGroups(): Response<com.chocolate.repository.dto.remote.users.response.UserGroupsDto>

    @POST("user_groups/create")
    suspend fun createUserGroup(
        @Query("name") name: String,
        @Query("description") description: String,
        @Query("members") members:String
    ): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto>

    @PATCH("user_groups/{user_group_id}")
    suspend fun updateUserGroup(
        @Path("user_group_id") userGroupId: Int,
        @Query("name") name: String,
        @Query("description") description: String
    ): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto>

    @DELETE("user_groups/{user_group_id}")
    suspend fun removeUserGroup(@Path("user_group_id") userGroupId: Int): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto>

    @PUT("user-groups/{id}/members")
    suspend fun updateUserGroupMembers(
        @Path("id") id: Int,
        @Query("add") add: List<Int>,
        @Query("delete") delete: List<Int>
    ): Response<com.chocolate.repository.dto.remote.users.response.ResponseStateDto>

    @POST("user_groups/{user_group_id}/subgroups")
    suspend fun updateUserGroupSubgroups(
        @Path("user_group_id") userGroupId: Int,
        @Query("add") add: List<Int>?,
        @Query("delete") delete: List<Int>?
    ): Response<com.chocolate.repository.dto.remote.users.response.SubgroupsOfUserGroupDto>

    @GET("user_groups/{groupId}/members/{userId}")
    suspend fun getUserMembership(
        @Path("groupId") groupId: Int,
        @Path("userId") userId: Int,
        @Query("direct_member_only") directMemberOnly: Boolean
    ): Response<com.chocolate.repository.dto.remote.users.response.UserMembershipStateDto>

    @GET("user_groups/{groupId}/memberships")
    suspend fun getUserGroupMemberships(
        @Path("groupId") groupId: Int,
        @Query("direct_member_only") directMemberOnly: Boolean
    ): Response<com.chocolate.repository.dto.remote.users.response.UserGroupMembershipsDto>
    @GET("user_groups/{id}/subgroups")
    suspend fun getSubgroupsOfUserGroup(
        @Path("id") id: Int,
        @Query("direct_subgroup_only") directSubgroupOnly: Boolean
    ): Response<com.chocolate.repository.dto.remote.users.response.SubgroupsOfUserGroupDto>

    @GET("users/me/alert_words")
    suspend fun getAlertWords(): Response<com.chocolate.repository.dto.remote.users.response.AlertWordsDto>

    @POST("users/me/alert_words")
    suspend fun addAlertWords(@Query("alert_words") alertWords: String): Response<com.chocolate.repository.dto.remote.users.response.AlertWordsDto>

    @DELETE("users/me/alert_words")
    suspend fun removeAlertWords(@Query("alert_words") alertWords: String): Response<com.chocolate.repository.dto.remote.users.response.AlertWordsDto>

    @POST("users/me/muted_users/{muted_user_id}")
    suspend fun muteUser(
        @Path("muted_user_id") mutedUserId: Int
    ): Response<com.chocolate.repository.dto.remote.users.response.MuteUserResponseDto>

    @DELETE("users/me/muted_users/{muted_user_id}")
    suspend fun unmuteUser(
        @Path("muted_user_id") mutedUserId: Int
    ): Response<com.chocolate.repository.dto.remote.users.response.MuteUserResponseDto>
}
