package com.chocolate.usecases.member

import repositories.MemberRepository
import javax.inject.Inject

class IsMemberLoggedInUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {
    suspend operator fun invoke(): Boolean {
        return memberRepository.isMemberLoggedIn()
    }
}