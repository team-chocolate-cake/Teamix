package com.chocolate.entities.exceptions

open class TeamixException(message: String?) : Exception(message)
open class NullDataException(message: String?) : TeamixException(message)

open class NetworkException(message: String?) : TeamixException(message)

class NoConnectionException(message: String?) : NetworkException(message)
class ServerException(message: String?) : NetworkException(message)

open class ValidationException(message: String?) : TeamixException(message)
class UserDeactivatedException(message: String?): ValidationException(message)
class UnAuthorizedException (message: String?): ValidationException(message)
class MissingRequiredFieldsException(message: String?): ValidationException(message)
class PasswordMismatchException(message: String?): ValidationException(message)
class InvalidUsernameException(message: String?): ValidationException(message)
class InvalidEmailException(message: String?): ValidationException(message)

object OrganizationNotFoundException: TeamixException(null)
object EmptyOrganizationNameException: TeamixException(null)
object InvalidOrganizationImageUrl: TeamixException(null)
object WrongEmailException: TeamixException(null)
object EmptyEmailException: TeamixException(null)
object EmptyPasswordException: TeamixException(null)
object EmptyMemberIdException: TeamixException(null)
object MemberNotFoundException: TeamixException(null)
object RegistrationFailedException: TeamixException(null)
object MemberAlreadyExistException: TeamixException(null)
object WrongEmailOrPasswordException: TeamixException(null)
object EmptyImageUriException: TeamixException(null)