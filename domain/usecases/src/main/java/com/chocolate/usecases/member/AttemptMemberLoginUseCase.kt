package com.chocolate.usecases.member

import com.chocolate.entities.utils.EmptyEmailException
import com.chocolate.entities.utils.EmptyPasswordException
import repositories.MemberRepository
import javax.inject.Inject

class AttemptMemberLoginUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {
    suspend operator fun invoke(email: String, password: String): Boolean {
        if (email.isBlank()) {
            throw EmptyEmailException

        } else if (password.isBlank()) {
            throw EmptyPasswordException
        }
        memberRepository.loginMember(email, password)
        return true
    }
}