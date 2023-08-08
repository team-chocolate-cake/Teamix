package repositories.draft

import com.chocolate.entities.draft.Draft

interface DraftRepository {
    suspend fun getDrafts(): List<Draft>


    suspend fun createDraft(
        id: Int,
        type: String,
        to: List<Int>,
        topic: String,
        content: String,
        timestamp: Long
    ): List<Int>

    suspend fun editDraft(
        id: Int,
        type: String,
        to: List<Int>,
        topic: String,
        content: String,
        timestamp: Long
    )

    suspend fun deleteDraft(id: Int)
}