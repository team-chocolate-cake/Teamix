package com.chocolate.repository.service

import com.chocolate.repository.dto.draft.response.BaseDraftResponse
import com.chocolate.repository.dto.draft.response.DraftsDto
import retrofit2.Response

interface DraftMessageDataSource {

    suspend fun getDrafts(): Response<DraftsDto>


    suspend fun deleteDraft(id: Int): Response<BaseDraftResponse>
    suspend fun createDraft(
        id: Int,
        type: String,
        to: String,
        topic: String,
        content: String,
        timestamp: Long
    ): Response<BaseDraftResponse>

    suspend fun editDraft(
        id: Int,
        type: String,
        to: String,
        topic: String,
        content: String,
        timestamp: Long
    ): Response<BaseDraftResponse>
}
