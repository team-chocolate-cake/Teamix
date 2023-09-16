package com.chocolate.usecases.member

import repositories.MemberRepository
import javax.inject.Inject

class UpdateMemberImageUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {

    suspend operator fun invoke(imageUri: String) = memberRepository.updateMemberPicture(imageUri)


}