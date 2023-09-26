package com.chocolate.usecases.member

import repositories.MemberRepository
import javax.inject.Inject

class GetIdOfCurrentMemberUseCase @Inject constructor(
    private val repository: MemberRepository
){
    suspend operator fun invoke() = repository.getIdOfCurrentMember()
}