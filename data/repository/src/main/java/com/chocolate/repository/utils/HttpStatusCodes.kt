package com.chocolate.repository.utils

enum class HttpStatusCodes(val code: Int) {
    BAD_REQUEST(400),
    NO_CONNECTION(404),
    UNAUTHORIZED(401),
    USER_DEACTIVATED(403),
    TOO_MANY_REQUESTS(429)
}