package com.chocolate.usecases.usecase.member

import com.chocolate.entities.util.EmptyEmailException
import com.chocolate.entities.util.EmptyPasswordException
import com.chocolate.usecases.repositories.MemberRepository
import javax.inject.Inject

class AttemptMemberLoginUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {
    suspend operator fun invoke(email: String, password: String): Boolean {
        validateEmailAndPassword(email, password)
        memberRepository.loginMember(email, password)
        return true
    }

    private fun validateEmailAndPassword(email: String, password: String) {
        if (email.isBlank()) {
            throw EmptyEmailException
        } else if (password.isBlank()) {
            throw EmptyPasswordException
        }
    }
}