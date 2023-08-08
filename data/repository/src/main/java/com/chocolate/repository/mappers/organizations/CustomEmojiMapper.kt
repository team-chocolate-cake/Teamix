package com.chocolate.repository.mappers.organizations

import com.chocolate.entities.server_and_organizations.CustomEmoji
import com.chocolate.entities.server_and_organizations.EmojiEntity
import com.chocolate.repository.dto.remote.server_and_organizations.response.CustomEmojiDto

fun CustomEmojiDto.toEntity(): CustomEmoji {
    val emojiEntity = emoji?.mapValues { (_, emojiDto) ->
        EmojiEntity(
            authorId = emojiDto.authorId,
            deactivated = emojiDto.deactivated,
            id = emojiDto.id,
            name = emojiDto.name,
            sourceUrl = emojiDto.sourceUrl
        )
    } ?: emptyMap()
    return CustomEmoji(emoji = emojiEntity)
}