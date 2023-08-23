package repositories

import com.chocolate.entities.scheduled_messages.ScheduledMessage

interface ScheduledMessageRepository {
    suspend fun getScheduledMessages(): List<ScheduledMessage>

    suspend fun createScheduledMessage(
        type: String,
        to: Any,
        content: String,
        topic: String,
        scheduledDeliveryTimestamp: Long
    ): Int

    suspend fun editScheduledMessage(
        id: Int,
        type: String,
        to: Any,
        content: String,
        topic: String,
        scheduledDeliveryTimestamp: Long
    ): Int

    suspend fun deleteScheduledMessage(id: Int)

}