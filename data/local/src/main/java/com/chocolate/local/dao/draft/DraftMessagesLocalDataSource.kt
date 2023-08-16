package com.chocolate.local.dao.draft

import com.chocolate.repository.model.localDto.draft.DraftLocalDto
import com.chocolate.repository.service.local.DraftMessagesRoomDataSource
import javax.inject.Inject

class DraftMessagesLocalDataSource @Inject constructor(
    private val draftMessagesDao: DraftMessagesDao
) : DraftMessagesRoomDataSource {
    override suspend fun insertDraft(draft: DraftLocalDto) {
        draftMessagesDao.upsertDraft(draft)
    }

    override suspend fun getDraftById(id: String): DraftLocalDto? {
        return draftMessagesDao.getDraftById(id)
    }

    override suspend fun deleteDraftMessage(draftId: Int) {
        draftMessagesDao.deleteDraftMessage(draftId)
    }
}