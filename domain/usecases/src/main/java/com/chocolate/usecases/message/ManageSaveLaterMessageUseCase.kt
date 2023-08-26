package com.chocolate.usecases.message

import com.chocolate.entities.messages.Message
import repositories.MessagesRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class ManageSaveLaterMessageUseCase @Inject constructor(
    private val messagesRepository: MessagesRepository
) {
    suspend fun saveMassage(message: Message) = messagesRepository.saveMessage(message)

    suspend fun getSavedMessages(): Map<String, List<Message>> {
       val messages =  messagesRepository.getSavedMessages().sortedByDescending { it.timestamp }
       val groupedMessages = messages.groupBy { message ->
            formatDate(message.timestamp)
        }

        return groupedMessages
    }

    suspend fun deleteSavedMessageById(id: Int) = messagesRepository.deleteMessage(id)

    private fun formatDate(date: Date): String {
        val dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.US)
        return dateFormat.format(date)
    }
}