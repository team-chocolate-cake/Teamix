package com.chocolate.repository.service.remote

import com.chocolate.repository.dto.remote.draft.response.BaseDraftResponse
import com.chocolate.repository.dto.remote.draft.response.DraftsDto
import retrofit2.Response

interface DraftMessageDataSource {

    suspend fun getDrafts(): Response<com.chocolate.repository.dto.remote.draft.response.DraftsDto>

    suspend fun createDraft(
        draftRequest: String
    ): Response<com.chocolate.repository.dto.remote.draft.response.BaseDraftResponse>

    suspend fun editDraft(
        id: Int,
        draftRequest: String
    ): Response<com.chocolate.repository.dto.remote.draft.response.BaseDraftResponse>

    suspend fun deleteDraft(id: Int): Response<com.chocolate.repository.dto.remote.draft.response.BaseDraftResponse>
}
