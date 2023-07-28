package repositories.draft

interface DraftRepository {
    suspend fun getDrafts()

    suspend fun createDraft()

    suspend fun editDraft()

    suspend fun deleteDraft()
}