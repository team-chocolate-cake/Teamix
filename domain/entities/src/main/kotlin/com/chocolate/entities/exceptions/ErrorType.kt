package com.chocolate.entities.exceptions

open class TeamixException(message: String?) : Exception(message)
open class NetworkException(message: String?) : TeamixException(message)
open class ValidationException(message: String?) : TeamixException(message)
open class NullDataException(message: String?) : TeamixException(message)




class NoConnectionException(message: String?) : NetworkException(message)
open class ServerException(message: String?) : NetworkException(message)
class UserDeactivatedException(message: String?) : ServerException(message)
class RateLimitExceededException(message: String?) : ServerException(message)


class UnAuthorizedException(message: String?) : ValidationException(message)
class InvalidUserNameAndPassword(message: String?) : ValidationException(message)



class UnknownException(message: String) : TeamixException(message)



