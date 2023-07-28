package repositories.scheduled_message

interface ScheduledMessageRepository {
    suspend fun getScheduledMessages()

    suspend fun createScheduledMessage()

    suspend fun editScheduledMessage()

    suspend fun deleteScheduledMessage()

}