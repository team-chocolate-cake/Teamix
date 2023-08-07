package com.chocolate.repository.mapper.users.response

import com.chocolate.entities.user.respons.ResponseState
import com.chocolate.repository.dto.users.response.ResponseStateDto

fun ResponseStateDto.toResponseState(): ResponseState {
    return ResponseState(
        code=code, message=message, result=result
    )
}