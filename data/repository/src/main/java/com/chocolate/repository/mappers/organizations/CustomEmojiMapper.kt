package com.chocolate.repository.mappers.organizations

import com.chocolate.entities.server_and_organizations.CustomEmoji
import com.chocolate.entities.server_and_organizations.Emoji
import com.chocolate.repository.dto.remote.server_and_organizations.response.CustomEmojiDto

fun CustomEmojiDto.toCustomEmoji(): CustomEmoji {
    val emojiEntity = emojiDto?.mapValues { (_, emojiDto) ->
        Emoji(
            authorId = emojiDto.authorId ?: 0,
            deactivated = emojiDto.deactivated ?: false,
            id = emojiDto.id ?: "",
            name = emojiDto.name ?: "",
            sourceUrl = emojiDto.sourceUrl ?: ""
        )
    } ?: emptyMap()
    return CustomEmoji(emoji = emojiEntity)
}