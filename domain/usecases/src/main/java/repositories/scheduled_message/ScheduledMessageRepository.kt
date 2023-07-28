package repositories.scheduled_message

interface ScheduledMessageRepository {
    fun getScheduledMessages()

    fun createScheduledMessage()

    fun editScheduledMessage()

    fun deleteScheduledMessage()

}