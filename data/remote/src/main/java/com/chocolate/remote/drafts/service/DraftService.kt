package com.chocolate.remote.drafts.service

import com.chocolate.repository.dto.remote.draft.response.BaseDraftResponse
import com.chocolate.repository.dto.remote.draft.response.DraftsDto
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
        @Query("drafts") draftRequest: String
    ) : Response<BaseDraftResponse>

    @PATCH("drafts/{draft_id}")
    suspend fun editDraft(
        @Path("draft_id") id: Int,
        @Query("draft") draftRequest: String
    ): Response<BaseDraftResponse>

    @DELETE("drafts/{draft_id}")
    suspend fun deleteDraft(@Path("draft_id") id: Int): Response<BaseDraftResponse>

}