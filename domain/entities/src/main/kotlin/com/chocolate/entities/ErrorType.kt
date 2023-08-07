package com.chocolate.entities

open class TeamixException(message: String?) : Exception(message)

open class NetworkException(message: String?) : TeamixException(message)
class NoConnectionException(message: String?) : NetworkException(message)

open class ServerException(message: String?) : TeamixException(message)
class UnAuthorizedException(message: String?) : ServerException(message)
class UserDeactivatedException(message: String?) : ServerException(message)
class RateLimitExceededException(message: String?) : ServerException(message)

class UnknownException(message: String) : TeamixException(message)



