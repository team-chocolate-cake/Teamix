package com.chocolate.usecases.member

import com.chocolate.entities.utils.EmptyImageUriException
import com.chocolate.entities.utils.InvalidEmailException
import com.chocolate.entities.utils.InvalidUsernameException
import com.chocolate.entities.utils.MissingRequiredFieldsException
import com.chocolate.entities.utils.PasswordMismatchException
import com.chocolate.entities.Member
import com.chocolate.entities.utils.Empty
import repositories.MemberRepository
import javax.inject.Inject

class CreateMemberUseCase @Inject constructor(
    private val memberRepository: MemberRepository,
) {

    suspend operator fun invoke(member: Member, confirmPassword: String) {
        validateMemberInformation(member, confirmPassword)
        isValidUsername(member.name)
        validateEmail(member.email)
        validateImageUri(member.imageUrl)
        validatePassword(member.password, confirmPassword)

        memberRepository.createMember(member)
    }

    private fun validateImageUri(imageUri: String) {
        if (imageUri == "null" || imageUri.isBlank()) {
            throw EmptyImageUriException
        }
    }

    private fun validateMemberInformation(memberInfo: Member, confirmPassword: String) {
        if (memberInfo.name.isBlank() ||
            memberInfo.email.isBlank() ||
            memberInfo.password.isBlank() ||
            confirmPassword.isBlank()
        ) throw MissingRequiredFieldsException(String.Empty)

    }

    private fun validatePassword(password: String, confirmPassword: String) {
        if (password != confirmPassword) {
            throw PasswordMismatchException(String.Empty)
        }
    }

    private fun isValidUsername(username: String) {
        val pattern = Regex("^[a-zA-Z][a-zA-Z0-9_ ]{2,}$")
        if (!pattern.matches(username)) throw InvalidUsernameException(String.Empty)
    }

    private fun validateEmail(email: String) {
        val emailRegex = Regex("""^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Z|a-z]{2,}$""")
        if (!emailRegex.matches(email)) throw InvalidEmailException(String.Empty)
    }
}
