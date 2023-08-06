package com.chocolate.repository.implementation.draft

import com.chocolate.repository.implementation.BaseRepository
import com.chocolate.repository.service.DraftMessageDataSource
import repositories.draft.DraftRepository
import javax.inject.Inject

class DraftRepositoryImpl @Inject constructor(
    private val draftDataSource: DraftMessageDataSource
): DraftRepository, BaseRepository() {
    override suspend fun getDrafts() {
         wrapApiCall { draftDataSource.getDrafts() }
    }

    override suspend fun createDraft() {
       wrapApiCall { draftDataSource.createDraft() }
    }

    override suspend fun editDraft() {
        wrapApiCall { draftDataSource.editDraft() }
    }

    override suspend fun deleteDraft() {
      wrapApiCall { draftDataSource.deleteDraft() }
    }
}