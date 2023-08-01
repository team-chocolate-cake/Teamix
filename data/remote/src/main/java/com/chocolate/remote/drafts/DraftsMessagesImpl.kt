package com.chocolate.remote.drafts

import com.chocolate.remote.drafts.service.DraftService
import com.chocolate.repository.dto.remote.draft.response.BaseDraftResponse
import com.chocolate.repository.dto.remote.draft.response.DraftsDto
import com.chocolate.repository.service.remote.DraftMessageDataSource
import retrofit2.Response
import javax.inject.Inject

class DraftsMessagesImpl @Inject constructor(
    private val draftService: DraftService
): DraftMessageDataSource {
    override suspend fun getDrafts(): Response<com.chocolate.repository.dto.remote.draft.response.DraftsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun createDraft(draftRequest: String): Response<com.chocolate.repository.dto.remote.draft.response.BaseDraftResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun editDraft(id: Int, draftRequest: String): Response<com.chocolate.repository.dto.remote.draft.response.BaseDraftResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDraft(id: Int): Response<com.chocolate.repository.dto.remote.draft.response.BaseDraftResponse> {
        TODO("Not yet implemented")
    }
}