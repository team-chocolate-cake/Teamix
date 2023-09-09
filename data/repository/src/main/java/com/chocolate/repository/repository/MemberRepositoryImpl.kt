package com.chocolate.repository.repository

import com.chocolate.entities.member.Member
import com.chocolate.entities.exceptions.EmptyMemberIdException
import com.chocolate.entities.exceptions.EmptyOrganizationNameException
import com.chocolate.entities.exceptions.UserNotFoundException
import com.chocolate.entities.exceptions.WrongEmailException
import com.chocolate.repository.datastore.local.LocalDataSource
import com.chocolate.repository.datastore.local.PreferencesDataSource
import com.chocolate.repository.datastore.remote.AuthenticationDataSource
import com.chocolate.repository.datastore.remote.MemberRemoteDataSource
import com.chocolate.repository.mappers.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import repositories.MemberRepository
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val memberRemoteDataSource: MemberRemoteDataSource,
    private val authenticationDataSource: AuthenticationDataSource,
    private val preferencesDataSource: PreferencesDataSource,
    private val teamixLocalDataSource: LocalDataSource,
) : MemberRepository {

    private fun getCurrentOrganizationName(): String {
        return preferencesDataSource.getCurrentOrganizationName()
            ?: throw EmptyOrganizationNameException
    }

    override suspend fun setUserUsedAppForFirstTime(isComplete: Boolean) {
        preferencesDataSource.setUserUsedAppForFirstTime(isComplete)
    }

    override suspend fun checkIfUserUsedAppOrNot(): Flow<Boolean> {
        return preferencesDataSource.checkIfUserUsedAppOrNot()
    }

    override suspend fun getMembersInCurrentOrganization(): Flow<List<Member>> {
        return memberRemoteDataSource.getMembersInOrganizationByOrganizationName(
            getCurrentOrganizationName()
        ).map { it?.toEntity() ?: emptyList() }
    }

    override suspend fun getMemberInOrganizationByEmail(email: String): Member {
        return memberRemoteDataSource.getMemberInOrganizationByEmail(
            getCurrentOrganizationName(),
            email
        )?.toEntity() ?: throw WrongEmailException
    }

    override suspend fun loginMember(email: String, password: String) {
        memberRemoteDataSource.getMemberInOrganizationByEmail(getCurrentOrganizationName(), email)
            ?.toEntity().also {
                authenticationDataSource.loginUser(email, password)
            } ?: throw WrongEmailException
    }

    override fun isMemberLoggedIn(): Boolean {
        return authenticationDataSource.isUserLoggedIn()
    }

    override suspend fun logoutMember() {
        teamixLocalDataSource.deleteDataBase()
        authenticationDataSource.logoutUser()
    }

    override suspend fun getCurrentMember(): Member {
        val currentUserId =
            authenticationDataSource.getCurrentUserId() ?: throw EmptyMemberIdException
        val currentMember =
            memberRemoteDataSource.getMemberInOrganizationById(
                currentUserId,
                getCurrentOrganizationName()
            )?.toEntity()
        return currentMember ?: throw UserNotFoundException
    }
}