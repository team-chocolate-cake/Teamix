package repositories

import com.chocolate.entities.member.Member
import kotlinx.coroutines.flow.Flow

interface MemberRepository {

    suspend fun checkIfUserUsedAppOrNot(): Flow<Boolean>

    suspend fun setUserUsedAppForFirstTime(isComplete: Boolean)

    suspend fun getMembersInCurrentOrganization(): Flow<List<Member>>

    suspend fun getMemberInOrganizationByEmail(email: String, ): Member

    suspend fun loginMember(email: String, password: String)

    suspend fun isMemberLoggedIn(): Boolean

    suspend fun logoutMember()

    suspend fun getCurrentMember(): Member

    suspend fun createMember(member: Member): Member
    suspend fun updateMember(member: Member)
}
