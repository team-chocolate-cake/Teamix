package com.chocolate.entities.exceptions

open class TeamixException(message: String?) : Exception(message)
open class NetworkException(message: String?) : TeamixException(message)
open class ValidationException(message: String?) : TeamixException(message)
open class NullDataException(message: String?) : TeamixException(message)


class NoConnectionException(message: String?) : NetworkException(message)
open class ServerException(message: String?) : NetworkException(message)
class RateLimitExceededException(message: String?) : ServerException(message)
class RequestException(message: String?) : NetworkException(message)


object EmptyEmailException: TeamixException(null)
object EmptyFullNameException: TeamixException(null)
object SameUserDataException: TeamixException(null)


class UnknownException(message: String) : TeamixException(message)



