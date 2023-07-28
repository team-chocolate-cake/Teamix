package repositories.draft

interface DraftRepository {
    fun getDrafts()

    fun createDraft()

    fun editDraft()

    fun deleteDraft()
}