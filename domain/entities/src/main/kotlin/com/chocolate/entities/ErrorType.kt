package com.chocolate.entities

sealed class ErrorType(message: String) : Throwable(message) {

    class Network(message: String) : ErrorType(message)
    class NoConnection(message: String) : ErrorType(message)
    class UnAuthorized(message: String) : ErrorType(message)

    class UserDeactivated(message: String) : ErrorType(message)
    class RateLimitExceeded(message: String) : ErrorType(message)

    class Unknown(message: String) : ErrorType(message)


}