package com.chocolate.repository.repository

import com.chocolate.repository.datastore.PreferencesDataSource
import com.chocolate.repository.service.remote.RemoteDataSource
import repositories.ServerAndOrganizationsRepository
import javax.inject.Inject

class ServerAndOrganizationsRepositoryImpl @Inject constructor(
    private val organizationRemoteDataSource: RemoteDataSource,
    private val preferencesDataSource: PreferencesDataSource
) : ServerAndOrganizationsRepository{
    override suspend fun getOrganizationImage(): String {
        return organizationRemoteDataSource.getServerSettings().realmIcon ?:""
    }

//    override suspend fun getLinkifiers(): List<Linkifier> {
//        return organizationRemoteDataSource.getLinkifiers().linkifierDtos.toLinkifiers()
//    }

    override suspend fun addLinkifiers(pattern: String, url: String): Int {
        return organizationRemoteDataSource.addLinkifiers(pattern, url).id ?:-1
    }

    override suspend fun updateLinkifiers(
        filterId: Int,
        pattern: String,
        url: String
    ): Int {
        return organizationRemoteDataSource.updateLinkifiers(filterId, pattern, url).id ?:-1
    }

    override suspend fun deleteLinkifier(filterId: Int): Int {
        return organizationRemoteDataSource.deleteLinkifiers(filterId).id ?:-1
    }

    override suspend fun addCodePlayGround(
        name: String,
        language: String,
        url: String
    ): Int {
        return organizationRemoteDataSource.addCodePlayGround(name, language, url).id ?:-1
    }

    override suspend fun deleteCodePlayGround(playGRound: Int): Int {
        return organizationRemoteDataSource.deleteCodePlayground(playGRound).id ?:-1
    }

//    override suspend fun getAllCustomEmojis(): CustomEmoji {
//        return organizationRemoteDataSource.getAllCustomEmojis().toCustomEmoji()
//    }

//    override suspend fun addCustomEmoji(emojiName: String): DefaultOrganization {
//        return organizationRemoteDataSource.addCustomEmoji(emojiName).toDefaultOrganization()
//    }
//
//    override suspend fun deActivateCustomEmoji(emojiName: String): DefaultOrganization {
//        return organizationRemoteDataSource.deactivateCustomEmoji(emojiName).toDefaultOrganization()
//    }

//    override suspend fun getAllCustomProfileFields(): List<CustomFieldEntity> {
//        return organizationRemoteDataSource.getAllCustomProfileFields().customFieldDtos
//    }

    override suspend fun reorderCustomProfileFields(order: String): Int {
        return organizationRemoteDataSource.reorderCustomProfileFields(order).id ?:-1
    }

    override suspend fun createCustomProfileField(
        name: String,
        hint: String,
        fieldType: Int
    ): Int {
        return organizationRemoteDataSource.createCustomProfileField(name, hint, fieldType).id ?:-1
    }

    override suspend fun saveNameOrganizations(nameOrganizations: String) {
        preferencesDataSource.setOrganizationName(nameOrganizations)
    }

    override suspend fun getNameOrganizations(): String {
        return preferencesDataSource.currentOrganization().toString()
    }
}