package repositories.server_and_organizations

import com.chocolate.entities.server_and_organizations.LinkifiersEntity
import com.chocolate.entities.server_and_organizations.ServerSettings
import org.intellij.lang.annotations.Language

interface ServerAndOrganizationsRepository {
    suspend fun getServiceSettings(): ServerSettings
    suspend fun getLinkifiers(): LinkifiersEntity
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