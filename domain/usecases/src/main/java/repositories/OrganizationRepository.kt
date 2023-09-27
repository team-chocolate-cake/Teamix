package repositories

import com.chocolate.entities.entity.Organization

interface OrganizationRepository {
    suspend fun getOrganizationByName(organizationName: String): Organization?

    suspend fun saveOrganizationName(nameOrganizations: String)

    suspend fun getOrganizationName(): String

    suspend fun getOrganizationImage(): String

    suspend fun createOrganization(organization: Organization)
}