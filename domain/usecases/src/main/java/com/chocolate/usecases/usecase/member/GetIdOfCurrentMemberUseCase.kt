package com.chocolate.usecases.usecase.member

import com.chocolate.usecases.repositories.MemberRepository
import javax.inject.Inject

class GetIdOfCurrentMemberUseCase @Inject constructor(
    private val repository: MemberRepository
){
    suspend operator fun invoke() = repository.getIdOfCurrentMember()
}