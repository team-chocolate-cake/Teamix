package com.chocolate.repository.service.local

import com.chocolate.repository.model.localDto.draft.DraftLocalDto

interface DraftMessagesRoomDataSource {

    suspend fun insertDraft(draft: DraftLocalDto)

    suspend fun getDraftById(id: String): DraftLocalDto?

    suspend fun deleteDraftMessage(draftId: Int)
}