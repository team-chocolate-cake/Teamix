package repositories

import com.chocolate.entities.organization.Organization

interface OrganizationsRepository {

    suspend fun getOrganizationByName(organizationName: String): Organization?
    suspend fun saveOrganizationName(nameOrganizations: String)
    suspend fun getOrganizationName(): String
    suspend fun getOrganizationImage(): String
    suspend fun createOrganization(organization: Organization)

}