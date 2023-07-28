package com.chocolate.remote.drafts.service

import com.chocolate.remote.drafts.dto.DraftsDto
import com.chocolate.remote.drafts.response.BaseDraftResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface DraftService {

    @GET("drafts")
    fun getDrafts(): DraftsDto

    @POST("drafts")
    fun createDraft(
        @Query("type") type: String,
        @Query("to") to: List<Int>,
        @Query("topic") topic: String,
        @Query("content") content: String
    ) : BaseDraftResponse

    @PATCH("drafts/{draft_id}")
    fun editDraft(
        @Path("draft_id") id: Int,
        @Query("type") type: String,
        @Query("to") to: List<Int>,
        @Query("topic") topic: String,
        @Query("content") content: String,
    ): BaseDraftResponse

    @DELETE("drafts/{draft_id}")
    fun deleteDraft(@Path("draft_id") id: Int): BaseDraftResponse

}