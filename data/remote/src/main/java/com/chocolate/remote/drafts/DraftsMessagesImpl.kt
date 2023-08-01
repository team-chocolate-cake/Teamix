package com.chocolate.remote.drafts

import com.chocolate.remote.drafts.service.DraftService
import com.chocolate.repository.dto.draft.response.BaseDraftResponse
import com.chocolate.repository.dto.draft.response.DraftsDto
import com.chocolate.repository.service.remote.DraftMessageDataSource
import retrofit2.Response
import javax.inject.Inject

class DraftsMessagesImpl @Inject constructor(
    private val draftService: DraftService
): DraftMessageDataSource {
    override suspend fun getDrafts(): Response<DraftsDto> {
        TODO("Not yet implemented")
    }

    override suspend fun createDraft(draftRequest: String): Response<BaseDraftResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun editDraft(id: Int, draftRequest: String): Response<BaseDraftResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDraft(id: Int): Response<BaseDraftResponse> {
        TODO("Not yet implemented")
    }
}