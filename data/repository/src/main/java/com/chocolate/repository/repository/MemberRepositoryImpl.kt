package com.chocolate.repository.repository

import com.chocolate.entities.member.Member
import com.chocolate.entities.exceptions.EmptyMemberIdException
import com.chocolate.entities.exceptions.EmptyOrganizationNameException
import com.chocolate.entities.exceptions.MemberAlreadyExistException
import com.chocolate.entities.exceptions.MemberNotFoundException
import com.chocolate.entities.exceptions.WrongEmailException
import com.chocolate.entities.exceptions.WrongEmailOrPasswordException
import com.chocolate.repository.datastore.local.LocalDataSource
import com.chocolate.repository.datastore.local.PreferencesDataSource
import com.chocolate.repository.datastore.remote.MemberRemoteDataSource
import com.chocolate.repository.datastore.remote.OrganizationRemoteDataSource
import com.chocolate.repository.mappers.toEntity
import com.chocolate.repository.mappers.toRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import repositories.MemberRepository
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val memberRemoteDataSource: MemberRemoteDataSource,
    private val organizationRemoteDataSource: OrganizationRemoteDataSource,
    private val preferencesDataSource: PreferencesDataSource,
    private val teamixLocalDataSource: LocalDataSource,
) : MemberRepository {

    private suspend fun getCurrentOrganizationName(): String {
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
            ?.let { memberDto ->
                memberDto.toEntity().also { member ->
                    if (password == member.password) {
                        preferencesDataSource.setMemberLoggedIn()
                        preferencesDataSource.saveIdOfCurrentMember(member.id)
                    } else throw WrongEmailOrPasswordException
                }
            } ?: throw WrongEmailException
    }

    override suspend fun isMemberLoggedIn(): Boolean {
        return preferencesDataSource.isMemberLoggedIn()
    }

    override suspend fun logoutMember() {
        teamixLocalDataSource.deleteDataBase()
        preferencesDataSource.clearMemberData()
    }

    override suspend fun getCurrentMember(): Member {
        return preferencesDataSource.getIdOfCurrentMember()?.let { currentUserId ->
            memberRemoteDataSource.getMemberInOrganizationById(
                currentUserId,
                getCurrentOrganizationName()
            )?.toEntity() ?: throw MemberNotFoundException
        } ?: throw EmptyMemberIdException
    }

    override suspend fun createMember(member: Member): Member {
        memberRemoteDataSource.getMemberInOrganizationByEmail(
            getCurrentOrganizationName(),
            member.email
        )?.let { throw MemberAlreadyExistException }
            ?: organizationRemoteDataSource.addMemberInOrganization(
                member.toRemote(),
                getCurrentOrganizationName()
            ).also {
                memberRemoteDataSource.addMembersInChannel(
                    getCurrentOrganizationName(),
                    listOf(member.id),
                    "0"
                )
            }
        return member
    }

}