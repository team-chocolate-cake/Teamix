package com.chocolate.usecases.usecase.member

import com.chocolate.usecases.repositories.MemberRepository
import javax.inject.Inject

class UpdateMemberInformationUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {

    suspend operator fun invoke(userName: String): String {
        val member = memberRepository.getCurrentMember()
        memberRepository.updateMember(member.copy(name = userName))
        return userName
    }

}