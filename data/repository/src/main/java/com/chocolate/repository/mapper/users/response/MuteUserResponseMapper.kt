package com.chocolate.repository.mapper.users.response

import com.chocolate.entities.user.respons.MuteUserResponse
import com.chocolate.repository.dto.users.response.MuteUserResponseDto

fun MuteUserResponseDto.toMuteUserResponse(): MuteUserResponse {
return MuteUserResponse(message, result)
}