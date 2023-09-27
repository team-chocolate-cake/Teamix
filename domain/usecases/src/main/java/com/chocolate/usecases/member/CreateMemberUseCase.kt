package com.chocolate.usecases.member

import com.chocolate.entities.exceptions.EmptyEmailException
import com.chocolate.entities.exceptions.EmptyImageUriException
import com.chocolate.entities.exceptions.EmptyPasswordException
import com.chocolate.entities.exceptions.InvalidEmailException
import com.chocolate.entities.exceptions.InvalidUsernameException
import com.chocolate.entities.exceptions.PasswordMismatchException
import com.chocolate.entities.member.Member
import com.chocolate.entities.uills.Empty
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
        val pattern = Regex("^[a-zA-Z][a-zA-Z0-9_ ]{2,}$")
        if (!pattern.matches(name))
            throw InvalidUsernameException(String.Empty)
        else if (name.isBlank())
            throw InvalidUsernameException(null)
    }

    private fun validateEmail(email: String) {
        val emailRegex = Regex("""^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Z|a-z]{2,}$""")
        if (!emailRegex.matches(email))
            throw InvalidEmailException(String.Empty)
        else if (email.isBlank())
            throw EmptyEmailException
    }
}
