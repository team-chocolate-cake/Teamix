package com.chocolate.remote.users.service


import com.chocolate.repository.dto.users.request.ProfileData
import com.chocolate.repository.dto.users.request.SettingsRequest
import com.chocolate.repository.dto.users.response.AlertWordsDto
import com.chocolate.repository.dto.users.response.CreateUserDto
import com.chocolate.repository.dto.users.response.MuteUserResponseDto
import com.chocolate.repository.dto.users.response.OwnerUserDto
import com.chocolate.repository.dto.users.response.ResponseStateDto
import com.chocolate.repository.dto.users.response.SubgroupsOfUserGroupDto
import com.chocolate.repository.dto.users.response.UserAttachmentsDto
import com.chocolate.repository.dto.users.response.UserDto
import com.chocolate.repository.dto.users.response.UserGroupMembershipsDto
import com.chocolate.repository.dto.users.response.UserGroupsDto
import com.chocolate.repository.dto.users.response.UserMembershipStateDto
import com.chocolate.repository.dto.users.response.UserSettingsDto
import com.chocolate.repository.dto.users.response.UserStateDto
import com.chocolate.repository.dto.users.response.UsersDto
import com.chocolate.repository.dto.users.response.UsersStateDto
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
    ): Response<UsersDto>

    @GET("users/me")
    suspend fun getOwnUser(): Response<OwnerUserDto>

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") userId: Int,
        @Query("client_gravatar") clientGravatar: Boolean = true,
        @Query("include_custom_profile_fields") includeCustomProfileFields: Boolean = false
    ): Response<UserDto>

    @GET("users/{email}")
    suspend fun getUserByEmail(
        @Path("email") email: String,
        @Query("client_gravatar") clientGravatar: Boolean = true,
        @Query("include_custom_profile_fields") includeCustomProfileFields: Boolean = false
    ): Response<UserDto>

    @PATCH("users/{id}")
    suspend fun updateUserById(
        @Path("id") id: Int,
        @Query("full_name") fullName: String? = null,
        @Query("role") role: Int? = null,
        @Query("profile_data") profileData: List<ProfileData>? = null
    ): Response<ResponseStateDto>

    @POST("users/me/status")
    suspend fun updateUserStatus(
        @Query("status_text") statusText: String? = null,
        @Query("away") away: Boolean? = null,
        @Query("emoji_name") emojiName: String? = null,
        @Query("emoji_code") emojiCode: String? = null,
        @Query("reaction_type") reactionType: String? = null
    ): Response<ResponseStateDto>

    @POST("users")
    suspend fun createUser(
        @Query("email") email: String,
        @Query("password") password: String,
        @Query("full_name") fullName: String
    ): Response<CreateUserDto>

    @DELETE("users/{id}")
    suspend fun deactivateUser(@Path("id") id: Int): Response<ResponseStateDto>

    @POST("users/{id}/reactivate")
    suspend fun reactivateUser(@Path("id") id: Int): Response<ResponseStateDto>

    @DELETE("users/me")
    suspend fun deactivateOwnUser(): Response<ResponseStateDto>

    @POST("typing")
    suspend fun setTypingStatus(
        @Query("op") op: String,
        @Query("to") to: String,
        @Query("type") type: String? = "direct",
        @Query("topic") topic: String? = null
    ): Response<ResponseStateDto>

    @GET("users/{email}/presence")
    suspend fun getUserPresence(@Path("email") email: String): Response<UserStateDto>

    @GET("realm/presence")
    suspend fun getRealmPresence(): Response<UsersStateDto>

    @GET("attachments")
    suspend fun getAttachments(): Response<UserAttachmentsDto>

    @DELETE("attachments/{attachment_id}")
    suspend fun deleteAttachment(@Path("attachment_id") attachmentId: Int): Response<ResponseStateDto>

    @PATCH("settings")
    suspend fun updateSettings(@Query("settings") settings: SettingsRequest): Response<UserSettingsDto>

    @GET("user_groups")
    suspend fun getUserGroups(): Response<UserGroupsDto>

    @POST("user_groups/create")
    suspend fun createUserGroup(
        @Query("name") name: String,
        @Query("description") description: String,
        @Query("members") members:String
    ): Response<ResponseStateDto>

    @PATCH("user_groups/{user_group_id}")
    suspend fun updateUserGroup(
        @Path("user_group_id") userGroupId: Int,
        @Query("name") name: String,
        @Query("description") description: String
    ): Response<ResponseStateDto>

    @DELETE("user_groups/{user_group_id}")
    suspend fun removeUserGroup(@Path("user_group_id") userGroupId: Int): Response<ResponseStateDto>

    @PUT("user-groups/{id}/members")
    suspend fun updateUserGroupMembers(
        @Path("id") id: Int,
        @Query("add") add: List<Int>,
        @Query("delete") delete: List<Int>
    ): Response<ResponseStateDto>

    @POST("user_groups/{user_group_id}/subgroups")
    suspend fun updateUserGroupSubgroups(
        @Path("user_group_id") userGroupId: Int,
        @Query("add") add: List<Int>?,
        @Query("delete") delete: List<Int>?
    ): Response<SubgroupsOfUserGroupDto>

    @GET("user_groups/{groupId}/members/{userId}")
    suspend fun getUserMembership(
        @Path("groupId") groupId: Int,
        @Path("userId") userId: Int,
        @Query("direct_member_only") directMemberOnly: Boolean
    ): Response<UserMembershipStateDto>

    @GET("user_groups/{groupId}/memberships")
    suspend fun getUserGroupMemberships(
        @Path("groupId") groupId: Int,
        @Query("direct_member_only") directMemberOnly: Boolean
    ): Response<UserGroupMembershipsDto>
    @GET("user_groups/{id}/subgroups")
    suspend fun getSubgroupsOfUserGroup(
        @Path("id") id: Int,
        @Query("direct_subgroup_only") directSubgroupOnly: Boolean
    ): Response<SubgroupsOfUserGroupDto>

    @GET("users/me/alert_words")
    suspend fun getAlertWords(): Response<AlertWordsDto>

    @POST("users/me/alert_words")
    suspend fun addAlertWords(@Query("alert_words") alertWords: String): Response<AlertWordsDto>

    @DELETE("users/me/alert_words")
    suspend fun removeAlertWords(@Query("alert_words") alertWords: String): Response<AlertWordsDto>

    @POST("users/me/muted_users/{muted_user_id}")
    suspend fun muteUser(
        @Path("muted_user_id") mutedUserId: Int
    ): Response<MuteUserResponseDto>

    @DELETE("users/me/muted_users/{muted_user_id}")
    suspend fun unmuteUser(
        @Path("muted_user_id") mutedUserId: Int
    ): Response<MuteUserResponseDto>
}
