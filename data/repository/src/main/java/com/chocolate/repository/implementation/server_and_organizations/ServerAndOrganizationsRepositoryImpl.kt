package com.chocolate.repository.implementation.server_and_organizations

import com.chocolate.repository.implementation.BaseRepository
import com.chocolate.repository.service.OrganizationDataSource
import repositories.server_and_organizations.ServerAndOrganizationsRepository
import javax.inject.Inject

class ServerAndOrganizationsRepositoryImpl @Inject constructor(
    private val organizationDataSource: OrganizationDataSource
): ServerAndOrganizationsRepository, BaseRepository() {
    override suspend fun getServiceSettings() {
        TODO("Not yet implemented")
    }

    override suspend fun getLinkifiers() {
        TODO("Not yet implemented")
    }

    override suspend fun addLinkifiers(pattern: String, url: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateLinkifiers(pattern: String, url: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteLinkifier(filterId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun addCodePlayGround(name: String, language: String, url: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCodePlayGround(playGRound: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCustomEmojis() {
        TODO("Not yet implemented")
    }

    override suspend fun addCustomEmoji(emojiName: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deActivateCustomEmoji(emojiName: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCustomProfileFields() {
        TODO("Not yet implemented")
    }

    override suspend fun reorderCustomProfileFields(order: List<Int>) {
        TODO("Not yet implemented")
    }

    override suspend fun createCustomProfileField(name: String, hint: String, fieldType: Int) {
        TODO("Not yet implemented")
    }
}