package com.chocolate.repository.service.remote

import com.chocolate.repository.model.dto.draft.response.BaseDraftResponse
import com.chocolate.repository.model.dto.draft.response.DraftsDto
import retrofit2.Response

interface DraftMessageRemoteDataSource {

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
