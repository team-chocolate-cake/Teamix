package com.chocolate.entities

open class TeamixException(message: String?) : Exception(message)

open class NetworkException(message: String?) : TeamixException(message)
class NoConnectionException(message: String?) : NetworkException(message)

class UnAuthorizedException(message: String?) : TeamixException(message)

open class ServerException(message: String?) : TeamixException(message)
class UserDeactivatedException(message: String?) : ServerException(message)
class RateLimitExceededException(message: String?) : ServerException(message)

class UnknownException(message: String) : TeamixException(message)



