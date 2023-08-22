package com.chocolate.entities.exceptions

open class TeamixException(message: String?) : Exception(message)
open class NullDataException(message: String?) : TeamixException(message)
class UnknownException(message: String?) : TeamixException(message)


open class NetworkException(message: String?) : TeamixException(message)

class NoConnectionException(message: String?) : NetworkException(message)
open class ServerException(message: String?) : NetworkException(message)
class TimeOutException(message: String?): ServerException(message)

open class ValidationException(message: String?) : TeamixException(message)
class EmptyEmailException(message: String?): ValidationException(null)
class EmptyFullNameException(message: String?): ValidationException(null)
class SameUserDataException(message: String?): ValidationException(null)
class UserDeactivatedException(message: String?): ValidationException(message)



