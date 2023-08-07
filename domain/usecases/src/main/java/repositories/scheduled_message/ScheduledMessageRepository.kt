package repositories.scheduled_message

import com.chocolate.entities.scheduled_messages.ScheduledMessages

interface ScheduledMessageRepository {
    suspend fun getScheduledMessages():ScheduledMessages

    suspend fun createScheduledMessage()

    suspend fun editScheduledMessage()

    suspend fun deleteScheduledMessage()

}