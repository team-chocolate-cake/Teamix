package repositories

import com.chocolate.entities.Organization
import com.chocolate.entities.channel.Channel

interface OrganizationsRepository {
    suspend fun getOrganizationById(id:String): Organization
    suspend fun getOrganizaionsByMemberId(id:String): List<Organization>
    suspend fun addOrganization(organization: Organization)
    suspend fun deleteOrganizationbyId(id:String)
    suspend fun updateOrganization(organization: Organization)
    suspend fun addChannel(organizationId: Long, channel: Channel)
    suspend fun deleteChannelById(channelId: Long)

    suspend fun saveOrganizationName(nameOrganizations: String)
    suspend fun getOrganizationName(): String
    suspend fun getOrganizationImage(): String

}