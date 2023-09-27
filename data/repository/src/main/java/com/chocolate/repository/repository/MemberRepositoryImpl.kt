package com.chocolate.repository.repository

import com.chocolate.entities.utils.EmptyMemberIdException
import com.chocolate.entities.utils.EmptyOrganizationNameException
import com.chocolate.entities.utils.MemberAlreadyExistException
import com.chocolate.entities.utils.MemberNotFoundException
import com.chocolate.entities.utils.WrongEmailException
import com.chocolate.entities.utils.WrongEmailOrPasswordException
import com.chocolate.entities.Member
import com.chocolate.repository.datasource.local.PreferencesDataSource
import com.chocolate.repository.datasource.remote.ChannelDataSource
import com.chocolate.repository.datasource.remote.MemberDataSource
import com.chocolate.repository.datasource.remote.OrganizationDataSource
import com.chocolate.repository.mappers.toEntity
import com.chocolate.repository.mappers.toRemote
import com.chocolate.repository.model.dto.channel.ChannelDto
import kotlinx.coroutines.flow.Flow
import repositories.MemberRepository
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val memberDataSource: MemberDataSource,
    private val organizationDataSource: OrganizationDataSource,
    private val preferencesDataSource: PreferencesDataSource,
    private val channelDataSource: ChannelDataSource
) : MemberRepository {

    private suspend fun getCurrentOrganizationName(): String {
        return preferencesDataSource.getCurrentOrganizationName()
            ?: throw EmptyOrganizationNameException
    }

    override suspend fun setUserUsedAppForFirstTime(isComplete: Boolean) {
        preferencesDataSource.setUserUsedAppForFirstTime(isComplete)
    }

    override fun checkIfUserUsedAppOrNot(): Flow<Boolean> {
        return preferencesDataSource.isUserUsedAppOrNot()
    }

    override suspend fun getMembersInOrganizationByOrganizationName(organizationName: String): Flow<List<Member>> {
        return memberDataSource.getMembersInOrganizationByOrganizationName(
            organizationName
        ).toEntity()
    }

    override suspend fun getMemberInOrganizationByEmail(email: String): Member {
        return memberDataSource.getMemberInOrganizationByEmail(
            getCurrentOrganizationName(),
            email
        )?.toEntity() ?: throw WrongEmailException
    }

    override suspend fun loginMember(email: String, password: String) {
        memberDataSource.getMemberInOrganizationByEmail(getCurrentOrganizationName(), email)
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
        preferencesDataSource.clearMemberData()
    }

    override suspend fun getCurrentMember(): Member {
        return preferencesDataSource.getIdOfCurrentMember()?.let { currentUserId ->
            memberDataSource.getMemberInOrganizationById(
                currentUserId,
                getCurrentOrganizationName()
            )?.toEntity() ?: throw MemberNotFoundException
        } ?: throw EmptyMemberIdException
    }

    override suspend fun createMember(member: Member): Member {
        memberDataSource.getMemberInOrganizationByEmail(
            getCurrentOrganizationName(),
            member.email
        )?.let { throw MemberAlreadyExistException }
            ?: organizationDataSource.addMemberInOrganization(
                member.toRemote(),
                getCurrentOrganizationName()
            ).also {
                subscribeInGeneralChannel(member)
            }
        return member
    }

    private suspend fun subscribeInGeneralChannel(member: Member) {
        val generalChannel = channelDataSource.getChannelInOrganizationByChannelName(
            getCurrentOrganizationName(),
            "General"
        )
        generalChannel?.let { addMemberInGeneralChannel(member) } ?: createGeneralChannel(member)
    }

    private suspend fun addMemberInGeneralChannel(member: Member) {
        memberDataSource.addMembersInChannel(
            getCurrentOrganizationName(),
            listOf(member.id),
            "0"
        )
    }

    private suspend fun createGeneralChannel(member: Member) {
        val generalChannelDto = ChannelDto(
            id = "0",
            name = "General",
            description = "Default channel",
            membersId = listOf(member.id),
        )
        channelDataSource.createChannel(generalChannelDto, getCurrentOrganizationName())
    }

    override suspend fun updateMember(member: Member) {
        memberDataSource.updateMember(getCurrentOrganizationName(), member.toRemote())
    }

    override suspend fun updateMemberPicture(imageUri: String) {
        memberDataSource.updateMemberImage(
            getCurrentOrganizationName(),
            getCurrentMember().copy(imageUrl = imageUri).toRemote()
        )
    }

    override suspend fun getIdOfCurrentMember(): String {
        return preferencesDataSource.getIdOfCurrentMember() ?: throw EmptyMemberIdException
    }
}