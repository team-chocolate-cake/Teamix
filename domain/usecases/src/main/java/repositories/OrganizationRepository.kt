package repositories

import com.chocolate.entities.Organization

interface OrganizationRepository {
    suspend fun getOrganizationByName(organizationName: String): Organization?

    suspend fun saveOrganizationName(nameOrganizations: String)

    suspend fun getCurrentOrganizationName(): String

    suspend fun getOrganizationImage(): String

    suspend fun createOrganization(organization: Organization)
}