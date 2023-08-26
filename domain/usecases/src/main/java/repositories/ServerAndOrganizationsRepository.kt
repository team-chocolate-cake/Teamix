package repositories


interface ServerAndOrganizationsRepository {
    suspend fun getOrganizationImage(): String
    //suspend fun getLinkifiers(): List<Linkifier>
    suspend fun addLinkifiers(pattern: String, url: String): Int
    suspend fun updateLinkifiers(filterId: Int, pattern: String, url: String): Int
    suspend fun deleteLinkifier(filterId: Int): Int
    suspend fun addCodePlayGround(name: String, language: String, url: String): Int
    suspend fun deleteCodePlayGround(playGRound: Int): Int
    suspend fun reorderCustomProfileFields(order: String): Int
    suspend fun createCustomProfileField(
        name: String,
        hint: String,
        fieldType: Int
    ): Int
    suspend fun saveOrganizationName(nameOrganizations: String)
    suspend fun getOrganizationName(): String
}