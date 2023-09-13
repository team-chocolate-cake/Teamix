package com.chocolate.usecases.member

import repositories.MemberRepository
import javax.inject.Inject

class AttemptMemberLogoutUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {
    suspend operator fun invoke(){
        memberRepository.logoutMember()
    }
}