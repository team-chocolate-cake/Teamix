package com.chocolate.repository.mapper.users.response

import com.chocolate.entities.user.AlertWords
import com.chocolate.repository.dto.users.response.AlertWordsDto

fun AlertWordsDto.toAlertWords(): AlertWords {
return AlertWords(alertWords ?: emptyList())
}