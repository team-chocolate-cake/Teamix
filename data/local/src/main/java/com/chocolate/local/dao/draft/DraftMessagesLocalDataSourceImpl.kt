package com.chocolate.local.dao.draft

import com.chocolate.repository.dto.local.draft.DraftLocalDto
import com.chocolate.repository.service.local.DraftMessagesLocalDataSource
import javax.inject.Inject

class DraftMessagesLocalDataSourceImpl @Inject constructor(
    private val draftMessagesDao: DraftMessagesDao
) : DraftMessagesLocalDataSource {
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