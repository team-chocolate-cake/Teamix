package com.chocolate.usecases.emojis

import repositories.MessagesRepository
import javax.inject.Inject

class ManageEmojisUseCase @Inject constructor(
    private val messagesRepository: MessagesRepository
) {
    suspend fun addEmojiReaction(messageId: Int, emojiName: String) {
        messagesRepository.addEmojiReaction(messageId, emojiName)
    }

    suspend fun deleteEmojiReaction(messageId: Int, emojiName: String) {
        messagesRepository.deleteEmojiReaction(messageId, emojiName)
    }
}