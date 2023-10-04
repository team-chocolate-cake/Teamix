package com.chocolate.usecases.usecase.member

import com.chocolate.usecases.repositories.MemberRepository
import javax.inject.Inject

class UpdateMemberImageUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {

    suspend operator fun invoke(imageUri: String) = memberRepository.updateMemberPicture(imageUri)


}