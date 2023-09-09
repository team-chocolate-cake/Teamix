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