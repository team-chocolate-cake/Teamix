package com.chocolate.viewmodel.profile

enum class ErrorCode(val code: String) {
    THE_SAME_DATA("100"),
    FAILED_EMAIL_WHEN_EMPTY("200"),
    FAILED_FULL_NAME_WHEN_EMPTY("300")
}