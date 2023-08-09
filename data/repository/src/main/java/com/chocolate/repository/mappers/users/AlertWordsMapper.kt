package com.chocolate.repository.mappers.users

import com.chocolate.entities.user.AlertWords
import com.chocolate.repository.model.dto.users.response.AlertWordsDto

fun AlertWordsDto.toAlertWords(): AlertWords {
    return AlertWords(alertWords ?: emptyList())
}