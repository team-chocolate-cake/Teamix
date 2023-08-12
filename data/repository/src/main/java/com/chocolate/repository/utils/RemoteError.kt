package com.chocolate.repository.utils

open class RemoteException(message: String?): Exception(message)

class NullResultException(message: String?) : RemoteException(message)
class BadRequestException(message: String?) : RemoteException(message)
open class ValidationException(message: String?): RemoteException(message)
class NotFoundException(message: String?): RemoteException(message)
class USerDeactivatedException(message: String?):RemoteException(message)
class TooManyRequestsException(message: String?):RemoteException(message)
open class NetworkException(message: String?): RemoteException(message)
class  NoInternetException(message: String?): NetworkException(message)
class ServerException(message: String?): NetworkException(message)