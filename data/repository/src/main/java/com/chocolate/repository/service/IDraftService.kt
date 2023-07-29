package com.chocolate.repository.service

import com.chocolate.repository.dto.draft.response.BaseDraftResponse
import com.chocolate.repository.dto.draft.response.DraftsDto
import retrofit2.Response

interface IDraftService {

    suspend fun getDrafts(): Response<DraftsDto>

    suspend fun createDraft(
        draftRequest: String
    ): Response<BaseDraftResponse>

    suspend fun editDraft(
        id: Int,
        draftRequest: String
    ): Response<BaseDraftResponse>

    suspend fun deleteDraft(id: Int): Response<BaseDraftResponse>
}
