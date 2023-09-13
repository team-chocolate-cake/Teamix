package com.chocolate.usecases.member

import repositories.MemberRepository
import javax.inject.Inject

class GetCurrentMemberUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {
    suspend operator fun invoke() = memberRepository.getCurrentMember()
}

