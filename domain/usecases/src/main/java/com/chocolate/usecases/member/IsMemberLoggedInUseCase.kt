package com.chocolate.usecases.member

import kotlinx.coroutines.flow.Flow
import repositories.MemberRepository
import javax.inject.Inject

class IsMemberLoggedInUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {
    suspend operator fun invoke(): Flow<Boolean> {
        return memberRepository.isMemberLoggedIn()
    }
}