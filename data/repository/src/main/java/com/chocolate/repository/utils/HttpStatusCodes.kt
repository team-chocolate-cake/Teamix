package com.chocolate.repository.utils

enum class HttpStatusCodes(val code: Int) {
    BAD_REQUEST(400),
    NOT_FOUND(404),
    UNAUTHORIZED(401),
    USER_DEACTIVATED(403),
}

