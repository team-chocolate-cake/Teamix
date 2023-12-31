package com.chocolate.entities.util

open class TeamixException(message: String?) : Exception(message)
open class NullDataException(message: String?) : TeamixException(message)

open class NetworkException(message: String?) : TeamixException(message)

class NoConnectionException(message: String?) : NetworkException(message)
class ServerException(message: String?) : NetworkException(message)
class FireBaseException(message: String?) : NetworkException(message)

open class ValidationException(message: String?) : TeamixException(message)
class UnAuthorizedException(message: String?) : ValidationException(message)
class PasswordMismatchException(message: String?) : ValidationException(message)
class InvalidUsernameException(message: String?) : ValidationException(message)
class InvalidEmailException(message: String?) : ValidationException(message)
object EmptyFullNameException : ValidationException(null)

object InvalidTopicNameException : TeamixException(null)

object OrganizationNotFoundException : TeamixException(null)
object ChannelAlreadyExistException : ValidationException(null)
object OrganizationAlreadyExistException : TeamixException(null)
object OrganizationNameIsSoLongException : TeamixException(null)
object EmptyOrganizationNameException : TeamixException(null)
object InvalidOrganizationImageUrl : TeamixException(null)
object InvalidChannelNameException : TeamixException(null)
object WrongEmailException : TeamixException(null)
object EmptyEmailException : TeamixException(null)
object EmptyPasswordException : TeamixException(null)
object EmptyMemberIdException : TeamixException(null)
object MemberNotFoundException : TeamixException(null)
object MemberAlreadyExistException : TeamixException(null)
object WrongEmailOrPasswordException : TeamixException(null)
object EmptyImageUriException : TeamixException(null)
object BlankSearchQueryException : TeamixException(null)