package repositories.server_and_organizations

import com.chocolate.entities.server_and_organizations.CustomEmoji
import com.chocolate.entities.server_and_organizations.CustomProfileFields
import com.chocolate.entities.server_and_organizations.DefaultOrganization
import com.chocolate.entities.server_and_organizations.LinkifiersEntity
import com.chocolate.entities.server_and_organizations.ServerSettings

interface ServerAndOrganizationsRepository {
    suspend fun getServiceSettings(): ServerSettings
    suspend fun getLinkifiers(): LinkifiersEntity
    suspend fun addLinkifiers(pattern: String, url: String): DefaultOrganization
    suspend fun updateLinkifiers(filterId: Int, pattern: String, url: String): DefaultOrganization
    suspend fun deleteLinkifier(filterId: Int): DefaultOrganization
    suspend fun addCodePlayGround(name: String, language: String, url: String): DefaultOrganization
    suspend fun deleteCodePlayGround(playGRound: Int): DefaultOrganization
    suspend fun getAllCustomEmojis():CustomEmoji
    suspend fun addCustomEmoji(emojiName: String): DefaultOrganization
    suspend fun deActivateCustomEmoji(emojiName: String): DefaultOrganization
    suspend fun getAllCustomProfileFields(): CustomProfileFields
    suspend fun reorderCustomProfileFields(order: String): DefaultOrganization
    suspend fun createCustomProfileField(
        name: String,
        hint: String,
        fieldType: Int
    ): DefaultOrganization
}