package com.chocolate.usecases.member

import com.chocolate.entities.util.EmptyImageUriException
import com.chocolate.entities.util.EmptyPasswordException
import com.chocolate.entities.util.InvalidEmailException
import com.chocolate.entities.util.PasswordMismatchException
import com.chocolate.entities.entity.Member
import com.chocolate.entities.util.Empty
import com.chocolate.entities.util.EmptyEmailException
import com.chocolate.entities.util.EmptyFullNameException
import repositories.MemberRepository
import javax.inject.Inject

class CreateMemberUseCase @Inject constructor(
    private val memberRepository: MemberRepository,
) {

    suspend operator fun invoke(member: Member, confirmPassword: String) {
        validateMemberInformation(member, confirmPassword)
        createMember(member)
    }

    suspend fun createMember(member: Member) {
        memberRepository.createMember(member)
    }

    private fun validateImageUri(imageUri: String) {
        if (imageUri == "null" || imageUri.isBlank()) {
            throw EmptyImageUriException
        }
    }

    fun validateMemberInformation(member: Member, confirmPassword: String) {
        isValidUsername(member.name)
        validateEmail(member.email)
        validateImageUri(member.imageUrl)
        validatePassword(member.password, confirmPassword)
    }

    private fun validatePassword(password: String, confirmPassword: String) {
        if (password != confirmPassword)
            throw PasswordMismatchException(String.Empty)
        else if (password.isBlank() || confirmPassword.isBlank())
            throw EmptyPasswordException
    }

    private fun isValidUsername(name: String) {
        if (name.isBlank())
            throw EmptyFullNameException
    }

    private fun validateEmail(email: String) {
        val emailRegex = Regex("""^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Z|a-z]{2,}$""")
        if (!emailRegex.matches(email))
            throw InvalidEmailException(String.Empty)
        else if (email.isBlank())
            throw EmptyEmailException
    }
}
