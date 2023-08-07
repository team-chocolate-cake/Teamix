package com.chocolate.repository.implementation.draft

import com.chocolate.repository.implementation.BaseRepository
import com.chocolate.repository.service.remote.DraftMessageDataSource
import repositories.draft.DraftRepository
import javax.inject.Inject

class DraftRepositoryImpl @Inject constructor(
    private val draftDataSource: DraftMessageDataSource
): DraftRepository, BaseRepository() {
    override suspend fun getDrafts() {
        TODO("Not yet implemented")
    }

    override suspend fun createDraft() {
        TODO("Not yet implemented")
    }

    override suspend fun editDraft() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDraft() {
        TODO("Not yet implemented")
    }
}