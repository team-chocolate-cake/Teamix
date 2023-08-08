package repositories.scheduled_message

import com.chocolate.entities.scheduled_messages.BaseScheduledMessage
import com.chocolate.entities.scheduled_messages.ScheduledMessages

interface ScheduledMessageRepository {
    suspend fun getScheduledMessages(): ScheduledMessages

    suspend fun createScheduledMessage(
        type: String,
        to: Any,
        content: String,
        topic: String,
        scheduledDeliveryTimestamp: Long
    ): BaseScheduledMessage

    suspend fun editScheduledMessage(
        id: Int,
        type: String,
        to: Any,
        content: String,
        topic: String,
        scheduledDeliveryTimestamp: Long
    ): BaseScheduledMessage

    suspend fun deleteScheduledMessage(id: Int): BaseScheduledMessage

}