package com.chocolate.remote.drafts.service

import com.chocolate.remote.drafts.dto.DraftsDto
import com.chocolate.remote.drafts.response.BaseDraftResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface DraftService {

    @GET("drafts")
    suspend fun getDrafts(): Response<DraftsDto>

    @POST("drafts")
    suspend fun createDraft(
        @Query("type") type: String,
        @Query("to") to: List<Int>,
        @Query("topic") topic: String,
        @Query("content") content: String
    ) : Response<BaseDraftResponse>

    @PATCH("drafts/{draft_id}")
    suspend fun editDraft(
        @Path("draft_id") id: Int,
        @Query("type") type: String,
        @Query("to") to: List<Int>,
        @Query("topic") topic: String,
        @Query("content") content: String,
    ): Response<BaseDraftResponse>

    @DELETE("drafts/{draft_id}")
    suspend fun deleteDraft(@Path("draft_id") id: Int): Response<BaseDraftResponse>

}