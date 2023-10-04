package com.chocolate.usecases.usecase.member

import com.chocolate.usecases.repositories.MemberRepository
import javax.inject.Inject

class GetCurrentMemberUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {
    suspend operator fun invoke() = memberRepository.getCurrentMember()
}

