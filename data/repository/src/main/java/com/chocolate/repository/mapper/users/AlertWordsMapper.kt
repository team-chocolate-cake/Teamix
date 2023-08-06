package com.chocolate.repository.mapper.users

import com.chocolate.entities.user.respons.AlertWords
import com.chocolate.repository.dto.users.response.AlertWordsDto

fun AlertWordsDto.toAlertWords(): AlertWords {
return AlertWords(alertWords)
}