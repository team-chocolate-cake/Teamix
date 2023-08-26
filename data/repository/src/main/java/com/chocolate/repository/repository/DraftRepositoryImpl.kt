package com.chocolate.repository.repository

import com.chocolate.entities.draft.Draft
import com.chocolate.repository.datastore.remote.RemoteDataSource
import com.chocolate.repository.mappers.draft.toEntity
import com.chocolate.repository.utils.toJson
import repositories.DraftRepository
import javax.inject.Inject

class DraftRepositoryImpl @Inject constructor(
    private val draftDataSource: RemoteDataSource
) : DraftRepository {
    override suspend fun getDrafts(): List<Draft> {
        return draftDataSource.getDrafts().drafts.toEntity()
    }

    override suspend fun createDraft(
        id: Int,
        type: String,
        to: List<Int>,
        topic: String,
        content: String,
        timestamp: Long
    ): List<Int> {
        return draftDataSource.createDraft(
            id = id,
            type = type,
            content = content,
            topic = topic,
            to = to.toJson(),
            timestamp = timestamp
        ).ids ?: emptyList()
    }

    override suspend fun editDraft(
        id: Int,
        type: String,
        to: List<Int>,
        topic: String,
        content: String,
        timestamp: Long
    ) {
        draftDataSource.editDraft(
            id = id,
            type = type,
            content = content,
            topic = topic,
            to = to.toJson(),
            timestamp = timestamp
        )
    }

    override suspend fun deleteDraft(id: Int) {
        draftDataSource.deleteDraft(id)
    }
}