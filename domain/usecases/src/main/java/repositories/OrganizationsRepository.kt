package repositories

import com.chocolate.entities.Organization
import com.chocolate.entities.channel.Channel

interface OrganizationsRepository {

    suspend fun getOrganizationByName(organizationName: String): Organization?
    suspend fun saveOrganizationName(nameOrganizations: String)
    /*suspend fun getOrganizationName(): String
    suspend fun getOrganizationImage(): String*/

}