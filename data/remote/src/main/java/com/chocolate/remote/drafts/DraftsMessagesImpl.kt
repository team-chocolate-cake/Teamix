package com.chocolate.remote.drafts

import com.chocolate.remote.drafts.service.DraftService
import com.chocolate.repository.dto.draft.response.BaseDraftResponse
import com.chocolate.repository.dto.draft.response.DraftsDto
import com.chocolate.repository.service.DraftMessageDataSource
import retrofit2.Response
import javax.inject.Inject

class DraftsMessagesImpl @Inject constructor(
    private val draftService: DraftService
) : DraftMessageDataSource {
    override suspend fun getDrafts(): Response<DraftsDto> {
        return draftService.getDrafts()
    }

    override suspend fun createDraft(
        id: Int,
        type: String,
        to: String,
        topic: String,
        content: String,
        timestamp: Long
    ): Response<BaseDraftResponse> {
        return draftService.createDraft(id, type, to, topic, content, timestamp)
    }

    override suspend fun editDraft(
        id: Int,
        type: String,
        to: String,
        topic: String,
        content: String,
        timestamp: Long
    ): Response<BaseDraftResponse> {
        return draftService.editDraft(id, type, to, topic, content, timestamp)
    }

    override suspend fun deleteDraft(id: Int): Response<BaseDraftResponse> {
        return draftService.deleteDraft(id)
    }
}