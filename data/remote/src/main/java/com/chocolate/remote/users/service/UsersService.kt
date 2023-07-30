package com.chocolate.remote.users.service


import com.chocolate.repository.dto.users.request.ProfileData
import com.chocolate.repository.dto.users.request.SettingsRequest
import com.chocolate.repository.dto.users.response.AlertWordsDTO
import com.chocolate.repository.dto.users.response.CreateUserDTO
import com.chocolate.repository.dto.users.response.MuteUserResponseDTO
import com.chocolate.repository.dto.users.response.OwnerUserDTO
import com.chocolate.repository.dto.users.response.ResponseStateDTO
import com.chocolate.repository.dto.users.response.SubgroupsOfUserGroupDTO
import com.chocolate.repository.dto.users.response.UserAttachmentsDTO
import com.chocolate.repository.dto.users.response.UserDTO
import com.chocolate.repository.dto.users.response.UserGroupMembershipsDTO
import com.chocolate.repository.dto.users.response.UserGroupsDTO
import com.chocolate.repository.dto.users.response.UserMembershipStateDTO
import com.chocolate.repository.dto.users.response.UserSettingsDTO
import com.chocolate.repository.dto.users.response.UserStateDTO
import com.chocolate.repository.dto.users.response.UsersDTO
import com.chocolate.repository.dto.users.response.UsersStateDTO
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
    ): Response<UsersDTO>


    @GET("users/me")
    suspend fun getOwnUser(): Response<OwnerUserDTO>

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") userId: Int,
        @Query("client_gravatar") clientGravatar: Boolean = true,
        @Query("include_custom_profile_fields") includeCustomProfileFields: Boolean = false
    ): Response<UserDTO>

    @GET("users/{email}")
    suspend fun getUserByEmail(
        @Path("email") email: String,
        @Query("client_gravatar") clientGravatar: Boolean = true,
        @Query("include_custom_profile_fields") includeCustomProfileFields: Boolean = false
    ): Response<UserDTO>

    @PATCH("users/{id}")
    suspend fun updateUserById(
        @Path("id") id: Int,
        @Query("full_name") fullName: String? = null,
        @Query("role") role: Int? = null,
        @Query("profile_data") profileData: List<ProfileData>? = null
    ): Response<ResponseStateDTO>

    @POST("users/me/status")
    suspend fun updateUserStatus(
        @Query("status_text") statusText: String? = null,
        @Query("away") away: Boolean? = null,
        @Query("emoji_name") emojiName: String? = null,
        @Query("emoji_code") emojiCode: String? = null,
        @Query("reaction_type") reactionType: String? = null
    ): Response<ResponseStateDTO>

    @POST("users")
    suspend fun createUser(
        @Query("email") email: String,
        @Query("password") password: String,
        @Query("full_name") fullName: String
    ): Response<CreateUserDTO>

    @DELETE("users/{id}")
    suspend fun deactivateUser(@Path("id") id: Int): Response<ResponseStateDTO>

    @POST("users/{id}/reactivate")
    suspend fun reactivateUser(@Path("id") id: Int): Response<ResponseStateDTO>

    @DELETE("users/me")
    suspend fun deactivateOwnUser(): Response<ResponseStateDTO>

    @POST("typing")
    suspend fun setTypingStatus(
        @Query("op") op: String,
        @Query("to") to: String,
        @Query("type") type: String? = "direct",
        @Query("topic") topic: String? = null
    ): Response<ResponseStateDTO>

    @GET("users/{email}/presence")
    suspend fun getUserPresence(@Path("email") email: String): Response<UserStateDTO>

    @GET("realm/presence")
    suspend fun getRealmPresence(): Response<UsersStateDTO>


    @GET("attachments")
    suspend fun getAttachments(): Response<UserAttachmentsDTO>



    @DELETE("attachments/{attachment_id}")
    suspend fun deleteAttachment(@Path("attachment_id") attachmentId: Int): Response<ResponseStateDTO>

    @PATCH("settings")
    suspend fun updateSettings(@Query("settings") settings: SettingsRequest): Response<UserSettingsDTO>


    @GET("user_groups")
    suspend fun getUserGroups(): Response<UserGroupsDTO>

    @POST("user_groups/create")
    suspend fun createUserGroup(
        @Query("name") name: String,
        @Query("description") description: String,
        @Query("members") members:String
    ): Response<ResponseStateDTO>

    @PATCH("user_groups/{user_group_id}")
    suspend fun updateUserGroup(
        @Path("user_group_id") userGroupId: Int,
        @Query("name") name: String,
        @Query("description") description: String
    ): Response<ResponseStateDTO>

    @DELETE("user_groups/{user_group_id}")
    suspend fun removeUserGroup(@Path("user_group_id") userGroupId: Int): Response<ResponseStateDTO>


    @PUT("user-groups/{id}/members")
    suspend fun updateUserGroupMembers(
        @Path("id") id: Int,
        @Query("add") add: List<Int>,
        @Query("delete") delete: List<Int>
    ): Response<ResponseStateDTO>

    @POST("user_groups/{user_group_id}/subgroups")
    suspend fun updateUserGroupSubgroups(
        @Path("user_group_id") userGroupId: Int,
        @Query("add") add: List<Int>?,
        @Query("delete") delete: List<Int>?
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
    ): Response<UserGroupMembershipsDTO>
    @GET("user_groups/{id}/subgroups")
    suspend fun getSubgroupsOfUserGroup(
        @Path("id") id: Int,
        @Query("direct_subgroup_only") directSubgroupOnly: Boolean
    ): Response<SubgroupsOfUserGroupDTO>

    @GET("users/me/alert_words")
    suspend fun getAlertWords(): Response<AlertWordsDTO>

    @POST("users/me/alert_words")
    suspend fun addAlertWords(@Query("alert_words") alertWords: String): Response<AlertWordsDTO>


    @DELETE("users/me/alert_words")
    suspend fun removeAlertWords(@Query("alert_words") alertWords: String): Response<AlertWordsDTO>

    @POST("users/me/muted_users/{muted_user_id}")
    suspend fun muteUser(
        @Path("muted_user_id") mutedUserId: Int
    ): Response<MuteUserResponseDTO>


    @DELETE("users/me/muted_users/{muted_user_id}")
    suspend fun unmuteUser(
        @Path("muted_user_id") mutedUserId: Int
    ): Response<MuteUserResponseDTO>
}
