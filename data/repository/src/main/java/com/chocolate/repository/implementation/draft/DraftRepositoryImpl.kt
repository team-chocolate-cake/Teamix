package com.chocolate.repository.implementation.draft

import com.chocolate.entities.draft.Draft
import com.chocolate.repository.dto.draft.request.DraftRequest
import com.chocolate.repository.implementation.BaseRepository
import com.chocolate.repository.mappers.Draft.toEntity
import com.chocolate.repository.service.DraftMessageDataSource
import com.google.gson.Gson
import repositories.draft.DraftRepository
import javax.inject.Inject

class DraftRepositoryImpl @Inject constructor(
    private val draftDataSource: DraftMessageDataSource
) : DraftRepository, BaseRepository() {
    override suspend fun getDrafts():List<Draft> {
       return wrapApiCall { draftDataSource.getDrafts() }.drafts.toEntity()
    }

    override suspend fun createDraft(
        id: Int,
        type: String,
        to: List<Int>,
        topic: String,
        content: String,
        timestamp: Long
    ) {

        wrapApiCall {
            draftDataSource.createDraft(
                id = id,
                type = type,
                content = content,
                topic = topic,
                to = to.toJson(),
                timestamp = timestamp
            )
        }
    }

    override suspend fun editDraft(
        id: Int,
        type: String,
        to: List<Int>,
        topic: String,
        content: String,
        timestamp: Long
    ) {
        wrapApiCall {
            draftDataSource.editDraft(
                id = id,
                type = type,
                content = content,
                topic = topic,
                to = to.toJson(),
                timestamp = timestamp
            )
        }
    }

    override suspend fun deleteDraft(id: Int) {
        wrapApiCall { draftDataSource.deleteDraft(id) }
    }
}

fun Any.toJson() = Gson().toJson(this)