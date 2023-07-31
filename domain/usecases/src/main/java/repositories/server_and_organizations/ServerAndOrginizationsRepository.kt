package repositories.server_and_organizations

import org.intellij.lang.annotations.Language

interface ServerAndOrganizationsRepository {
    suspend fun getServiceSettings()
    suspend fun getLinkifiers()
    suspend fun addLinkifiers(pattern: String, url: String)
    suspend fun updateLinkifiers(pattern: String, url: String)
    suspend fun deleteLinkifier(filterId: Int)
    suspend fun addCodePlayGround(name: String, language: String, url: String)
    suspend fun deleteCodePlayGround(playGRound: Int)
    suspend fun getAllCustomEmojis()
    suspend fun addCustomEmoji(emojiName: String)
    suspend fun deActivateCustomEmoji(emojiName: String)
    suspend fun getAllCustomProfileFields()
    suspend fun reorderCustomProfileFields(order: List<Int>)
    suspend fun createCustomProfileField(name: String, hint: String, fieldType: Int)
}