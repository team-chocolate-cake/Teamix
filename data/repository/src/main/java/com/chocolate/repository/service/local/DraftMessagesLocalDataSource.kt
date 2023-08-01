package com.chocolate.repository.service.local

import com.chocolate.repository.dto.local.draft.DraftLocalDto

interface DraftMessagesLocalDataSource {

    suspend fun insertDraft(draft: DraftLocalDto)

    suspend fun getDraftById(id: String): DraftLocalDto?

    suspend fun deleteSavedDraft(draft: DraftLocalDto)
}