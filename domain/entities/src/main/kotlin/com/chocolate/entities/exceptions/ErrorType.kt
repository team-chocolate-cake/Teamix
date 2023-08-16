package com.chocolate.entities.exceptions

open class TeamixException(message: String?) : Exception(message)
open class NetworkException(message: String?) : TeamixException(message)
open class ValidationException(message: String?) : TeamixException(message)
open class NullDataException(message: String?) : TeamixException(message)


class NoConnectionException(message: String?) : NetworkException(message)
open class ServerException(message: String?) : NetworkException(message)
class RateLimitExceededException(message: String?) : ServerException(message)
class RequestException(message: String?) : NetworkException(message)

class NullResultException(message: String?) : TeamixException(message)
class NotFoundException(message: String?): TeamixException(message)
class UserDeactivatedException(message: String?): TeamixException(message)
class TooManyRequestsException(message: String?): TeamixException(message)
class TimeoutException(message: String?): TeamixException(message)
class CertificateException(message: String?): TeamixException(message)

class UnknownException(message: String) : TeamixException(message)



