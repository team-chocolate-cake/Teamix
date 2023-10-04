package com.chocolate.usecases.usecase.member

import kotlinx.coroutines.flow.Flow
import com.chocolate.usecases.repositories.MemberRepository
import javax.inject.Inject

class IsMemberLoggedInUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {
    suspend operator fun invoke(): Flow<Boolean> {
        return memberRepository.isMemberLoggedIn()
    }
}