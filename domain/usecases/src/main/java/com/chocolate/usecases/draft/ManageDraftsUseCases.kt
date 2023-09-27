package com.chocolate.usecases.draft

import com.chocolate.entities.entity.Draft
import repositories.TopicsMessageRepository
import javax.inject.Inject

class ManageDraftsUseCases @Inject constructor(
    private val draftsRepository: TopicsMessageRepository
) {
    suspend fun getDrafts(): List<Draft> {
        return draftsRepository.getDrafts()
    }

    suspend fun deleteDraft(id: Int) {
        draftsRepository.deleteDraft(id)
    }

    suspend fun createDraft(
        type: String,
        recipients: Int,
        topic: String,
        content: String
    ):List<Int>{
        return draftsRepository.createDraft(type, recipients, topic, content)
    }
}